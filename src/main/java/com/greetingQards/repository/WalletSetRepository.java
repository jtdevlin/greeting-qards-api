package com.greetingQards.repository;

import com.greetingQards.service.wallet.WalletSet;
import org.springframework.data.jpa.repository.JpaRepository;

public interface WalletSetRepository extends JpaRepository<WalletSetEntity, String> {

}
