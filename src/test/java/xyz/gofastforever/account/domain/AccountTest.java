package xyz.gofastforever.account.domain;

import nl.jqno.equalsverifier.EqualsVerifier;
import nl.jqno.equalsverifier.Warning;
import org.junit.Test;

public class AccountTest extends AbstractJavaBeanTest<Account> {

  @Override
  protected Account getBeanInstance() { return new Account();}

  @Test
  public void equalsAndHashCodeContract() {
    EqualsVerifier.forClass(this.getBeanInstance().getClass())
        .suppress(new Warning[]{Warning.STRICT_INHERITANCE, Warning.NONFINAL_FIELDS})
        .withRedefinedSuperclass().verify();
  }

}