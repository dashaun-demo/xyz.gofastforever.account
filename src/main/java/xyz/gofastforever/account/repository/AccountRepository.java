package xyz.gofastforever.account.repository;

import xyz.gofastforever.account.domain.Account;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AccountRepository extends JpaRepository<Account, String> {

}
