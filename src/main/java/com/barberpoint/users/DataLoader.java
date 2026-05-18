package com.barberpoint.users;

import com.barberpoint.users.clientes.domain.entities.Cliente;
import com.barberpoint.users.barbeiros.domain.entities.Barbeiro;
import com.barberpoint.users.servicos.domain.entities.Servico;
import com.barberpoint.users.clientes.infrastructure.repository.ClienteRepository;
import com.barberpoint.users.barbeiros.infrastructure.repository.BarbeiroRepository;
import com.barberpoint.users.servicos.infrastructure.repository.ServicoRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Configuration
public class DataLoader {

    @Bean
    public CommandLineRunner loadData(
            ClienteRepository clienteRepository,
            BarbeiroRepository barbeiroRepository,
            ServicoRepository servicoRepository) {

        return args -> {
            if (clienteRepository.count() > 0) {
                System.out.println("[DataLoader] Dados já existem, pulando seed...");
                return;
            }

            System.out.println("[DataLoader] Inserindo dados de teste...");

            // Clientes
            Cliente c1 = new Cliente(); c1.setNome("João"); c1.setSobrenome("Silva");
            c1.setEmail("joao.silva@email.com"); c1.setTelefone("11999999999"); c1.setDataCriacao(LocalDateTime.now());
            clienteRepository.save(c1);

            Cliente c2 = new Cliente(); c2.setNome("Maria"); c2.setSobrenome("Santos");
            c2.setEmail("maria.santos@email.com"); c2.setTelefone("11888888888"); c2.setDataCriacao(LocalDateTime.now());
            clienteRepository.save(c2);

            Cliente c3 = new Cliente(); c3.setNome("Pedro"); c3.setSobrenome("Oliveira");
            c3.setEmail("pedro.oliveira@email.com"); c3.setTelefone("11777777777"); c3.setDataCriacao(LocalDateTime.now());
            clienteRepository.save(c3);

            System.out.println("[DataLoader] 3 clientes inseridos");

            // Barbeiros
            Barbeiro b1 = new Barbeiro(); b1.setNome("Carlos"); b1.setSobrenome("Ferreira");
            b1.setEmail("carlos@barberpoint.com"); b1.setTelefone("11666666666");
            b1.setEspecialidade("Corte Masculino"); b1.setDataCriacao(LocalDateTime.now());
            barbeiroRepository.save(b1);

            Barbeiro b2 = new Barbeiro(); b2.setNome("Roberto"); b2.setSobrenome("Almeida");
            b2.setEmail("roberto@barberpoint.com"); b2.setTelefone("11555555555");
            b2.setEspecialidade("Barba e Pintura"); b2.setDataCriacao(LocalDateTime.now());
            barbeiroRepository.save(b2);

            System.out.println("[DataLoader] 2 barbeiros inseridos");

            // Serviços
            Servico s1 = new Servico(); s1.setNome("Corte de Cabelo"); s1.setDescricao("Corte masculino tradicional");
            s1.setDuracao(30); s1.setPreco(new BigDecimal("50.00")); servicoRepository.save(s1);

            Servico s2 = new Servico(); s2.setNome("Barba"); s2.setDescricao("Barba modelada");
            s2.setDuracao(20); s2.setPreco(new BigDecimal("40.00")); servicoRepository.save(s2);

            Servico s3 = new Servico(); s3.setNome("Corte + Barba"); s3.setDescricao("Pacote completo");
            s3.setDuracao(60); s3.setPreco(new BigDecimal("80.00")); servicoRepository.save(s3);

            Servico s4 = new Servico(); s4.setNome("Pintura de Cabelo"); s4.setDescricao("Coloração masculina");
            s4.setDuracao(90); s4.setPreco(new BigDecimal("120.00")); servicoRepository.save(s4);

            System.out.println("[DataLoader] 4 serviços inseridos");
            System.out.println("[DataLoader] Seed concluído com sucesso!");
        };
    }
}