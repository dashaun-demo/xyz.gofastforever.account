package xyz.gofastforever.account.repository;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.tomakehurst.wiremock.client.WireMock;
import lombok.extern.slf4j.Slf4j;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.json.AutoConfigureJsonTesters;
import org.springframework.boot.test.autoconfigure.restdocs.AutoConfigureRestDocs;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import xyz.gofastforever.account.domain.Account;

import static org.hamcrest.CoreMatchers.containsString;
import static org.springframework.cloud.contract.wiremock.restdocs.WireMockRestDocs.verify;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@AutoConfigureMockMvc
@AutoConfigureJsonTesters
@AutoConfigureRestDocs("target/snippets")
@RunWith(SpringRunner.class)
@Slf4j
@SpringBootTest()
@WithMockUser("6d1057c5-9ae6-4dbf-bfd5-0e5a1d843d28")
public class AccountRepositoryTest {

  @Autowired
  private MockMvc mockMvc;

  @Autowired
  private AccountRepository accountRepository;

  @Before
  public void deleteAllBeforeTests() throws Exception {
    accountRepository.deleteAll();
  }

  @Test
  public void shouldReturnRepositoryIndex() throws Exception {
    MvcResult mvcResult = mockMvc.perform(get("/"))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$._links.accounts").exists())
        .andReturn();
  }

  @Test
  public void shouldCreateEntity() throws Exception {
    mockMvc.perform(post("/accounts")
        .content(new ObjectMapper().writeValueAsString(new Account())))
        .andExpect(status().isCreated())
        .andExpect(header().string("Location", containsString("accounts/")))
        .andDo(verify().wiremock(
            WireMock.post(WireMock.urlPathMatching("/accounts"))
        ).stub("create-account"))
        .andDo(document("create-account"));
  }

  @Test
  public void shouldRetrieveEntity() throws Exception {

    MvcResult mvcResult = mockMvc
        .perform(post("/accounts")
            .content(new ObjectMapper().writeValueAsString(new Account())))
        .andExpect(status().isCreated())
        .andReturn();

    String location = mvcResult.getResponse().getHeader("Location");
    mockMvc.perform(get(location)).andExpect(status().isOk())
        .andReturn();

  }

}