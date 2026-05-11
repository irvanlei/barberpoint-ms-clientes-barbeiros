package com.barberpoint.users;

import com.barberpoint.users.domain.entities.Cliente;
import com.barberpoint.users.domain.entities.Barbeiro;
import com.barberpoint.users.domain.entities.Servico;
import com.barberpoint.users.infrastructure.repository.ClienteRepository;
import com.barberpoint.users.infrastructure.repository.BarbeiroRepository;
import com.barberpoint.users.infrastructure.repository.ServicoRepository;
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
            // Verificar se já existem dados
            if (clienteRepository.count() > 0) {
                System.out.println("[DataLoader] Dados já existem, pulando seed...");
                return;
            }

            System.out.println("[DataLoader] Inserindo dados de teste...");

            // Inserir Clientes
            Cliente cliente1 = new Cliente();
            cliente1.setNome("João");
            cliente1.setSobrenome("Silva");
            cliente1.setEmail("joao.silva@email.com");
            cliente1.setTelefone("11999999999");
            cliente1.setDataCriacao(LocalDateTime.now());
            clienteRepository.save(cliente1);

            Cliente cliente2 = new Cliente();
            cliente2.setNome("Maria");
            cliente2.setSobrenome("Santos");
            cliente2.setEmail("maria.santos@email.com");
            cliente2.setTelefone("11888888888");
            cliente2.setDataCriacao(LocalDateTime.now());
            clienteRepository.save(cliente2);

            Cliente cliente3 = new Cliente();
            cliente3.setNome("Pedro");
            cliente3.setSobrenome("Oliveira");
            cliente3.setEmail("pedro.oliveira@email.com");
            cliente3.setTelefone("11777777777");
            cliente3.setDataCriacao(LocalDateTime.now());
            clienteRepository.save(cliente3);

            System.out.println("[DataLoader] 3 clientes inseridos");

            // Inserir Barbeiros
            Barbeiro barbeiro1 = new Barbeiro();
            barbeiro1.setNome("Carlos");
            barbeiro1.setSobrenome("Ferreira");
            barbeiro1.setEmail("carlos@barberpoint.com");
            barbeiro1.setTelefone("11666666666");
            barbeiro1.setEspecialidade("Corte Masculino");
            barbeiro1.setDataCriacao(LocalDateTime.now());
            barbeiroRepository.save(barbeiro1);

            Barbeiro barbeiro2 = new Barbeiro();
            barbeiro2.setNome("Roberto");
            barbeiro2.setSobrenome("Almeida");
            barbeiro2.setEmail("roberto@barberpoint.com");
            barbeiro2.setTelefone("11555555555");
            barbeiro2.setEspecialidade("Barba e Pintura");
            barbeiro2.setDataCriacao(LocalDateTime.now());
            barbeiroRepository.save(barbeiro2);

            System.out.println("[DataLoader] 2 barbeiros inseridos");

            // Inserir Serviços
            Servico servico1 = new Servico();
            servico1.setNome("Corte de Cabelo");
            servico1.setDescricao("Corte masculino tradicional");
            servico1.setDuracao(30);
            servico1.setPreco(new BigDecimal("50.00"));
            servicoRepository.save(servico1);

            Servico servico2 = new Servico();
            servico2.setNome("Barba");
            servico2.setDescricao("Barba modelada");
            servico2.setDuracao(20);
            servico2.setPreco(new BigDecimal("40.00"));
            servicoRepository.save(servico2);

            Servico servico3 = new Servico();
            servico3.setNome("Corte + Barba");
            servico3.setDescricao("Pacote completo");
            servico3.setDuracao(60);
            servico3.setPreco(new BigDecimal("80.00"));
            servicoRepository.save(servico3);

            Servico servico4 = new Servico();
            servico4.setNome("Pintura de Cabelo");
            servico4.setDescricao("Coloração masculina");
            servico4.setDuracao(90);
            servico4.setPreco(new BigDecimal("120.00"));
            servicoRepository.save(servico4);

            System.out.println("[DataLoader] 4 serviços inseridos");
            System.out.println("[DataLoader] Seed concluído com sucesso!");
        };
    }
}