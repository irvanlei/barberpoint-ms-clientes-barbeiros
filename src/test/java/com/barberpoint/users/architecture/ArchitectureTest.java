package com.barberpoint.users.architecture;

import com.tngtech.archunit.core.domain.JavaClasses;
import com.tngtech.archunit.core.importer.ClassFileImporter;
import com.tngtech.archunit.core.importer.ImportOption;
import com.tngtech.archunit.lang.ArchRule;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RestController;

import static com.tngtech.archunit.lang.syntax.ArchRuleDefinition.*;

/**
 * Testes de Arquitetura com ArchUnit.
 *
 * Valida as regras de Vertical Slice + Clean Architecture:
 *  - Domain não pode depender de Application nem Infrastructure
 *  - Application não pode depender de Infrastructure (apenas via Port)
 *  - API (Controllers) não pode depender de Infrastructure diretamente
 *  - Repositórios Spring ficam em infrastructure.repository
 *  - Portas (interfaces) ficam em domain.ports
 *  - Isolamento entre slices (clientes, barbeiros, servicos)
 */
@DisplayName("Testes de Arquitetura - Vertical Slice + Clean Architecture")
class ArchitectureTest {

    static final String BASE_PACKAGE = "com.barberpoint.users";

    static JavaClasses classes;

    @BeforeAll
    static void setup() {
        classes = new ClassFileImporter()
                .withImportOption(ImportOption.Predefined.DO_NOT_INCLUDE_TESTS)
                .importPackages(BASE_PACKAGE);
    }

    // ─── Regras de Camadas ─────────────────────────────────────────────────────

    @Test
    @DisplayName("Domain não deve depender de Infrastructure")
    void domainNaoDeveDependDeInfrastructure() {
        ArchRule rule = noClasses()
                .that().resideInAPackage("..domain..")
                .should().dependOnClassesThat().resideInAPackage("..infrastructure..");
        rule.check(classes);
    }

    @Test
    @DisplayName("Domain não deve depender de Application")
    void domainNaoDeveDependDeApplication() {
        ArchRule rule = noClasses()
                .that().resideInAPackage("..domain..")
                .should().dependOnClassesThat().resideInAPackage("..application..");
        rule.check(classes);
    }

    @Test
    @DisplayName("Application não deve depender diretamente de Infrastructure")
    void applicationNaoDeveDependDeInfrastructure() {
        ArchRule rule = noClasses()
                .that().resideInAPackage("..application..")
                .should().dependOnClassesThat().resideInAPackage("..infrastructure..");
        rule.check(classes);
    }

    // ─── Regras de Anotações ──────────────────────────────────────────────────

    @Test
    @DisplayName("Controllers devem ser anotados com @RestController")
    void controllersDevemSerAnotadosComRestController() {
        ArchRule rule = classes()
                .that().resideInAPackage("..clientes.api..")
                .or().resideInAPackage("..barbeiros.api..")
                .or().resideInAPackage("..servicos.api..")
                .and().haveSimpleNameEndingWith("Controller")
                .should().beAnnotatedWith(RestController.class);
        rule.check(classes);
    }

    @Test
    @DisplayName("Use Cases devem ser anotados com @Service")
    void useCasesDevemSerAnotadosComService() {
        ArchRule rule = classes()
                .that().resideInAPackage("..application.usecases..")
                .and().haveSimpleNameEndingWith("UseCase")
                .and().areNotAnnotatedWith(Deprecated.class)
                .should().beAnnotatedWith(Service.class);
        rule.check(classes);
    }

    // ─── Regras de Pacotes ─────────────────────────────────────────────────────

    @Test
    @DisplayName("Portas de repositório devem ser interfaces localizadas em domain.ports")
    void portasDevemSerInterfaces() {
        ArchRule rule = classes()
                .that().resideInAPackage("..domain.ports..")
                .should().beInterfaces();
        rule.check(classes);
    }

    @Test
    @DisplayName("Adapter de clientes deve implementar ClienteRepositoryPort")
    void adapterClienteDeveImplementarPorta() {
        ArchRule rule = classes()
                .that().resideInAPackage("..clientes.infrastructure.repository..")
                .and().haveSimpleNameEndingWith("Adapter")
                .should().implement(
                        com.barberpoint.users.clientes.domain.ports.ClienteRepositoryPort.class);
        rule.check(classes);
    }

    @Test
    @DisplayName("Adapter de barbeiros deve implementar BarbeiroRepositoryPort")
    void adapterBarbeiroDeveImplementarPorta() {
        ArchRule rule = classes()
                .that().resideInAPackage("..barbeiros.infrastructure.repository..")
                .and().haveSimpleNameEndingWith("Adapter")
                .should().implement(
                        com.barberpoint.users.barbeiros.domain.ports.BarbeiroRepositoryPort.class);
        rule.check(classes);
    }

    @Test
    @DisplayName("Adapter de serviços deve implementar ServicoRepositoryPort")
    void adapterServicoDeveImplementarPorta() {
        ArchRule rule = classes()
                .that().resideInAPackage("..servicos.infrastructure.repository..")
                .and().haveSimpleNameEndingWith("Adapter")
                .should().implement(
                        com.barberpoint.users.servicos.domain.ports.ServicoRepositoryPort.class);
        rule.check(classes);
    }

    // ─── Isolamento entre Slices ───────────────────────────────────────────────

    @Test
    @DisplayName("Slice clientes não deve depender do slice barbeiros")
    void clientesNaoDeveDependerDeBarbeiros() {
        ArchRule rule = noClasses()
                .that().resideInAPackage("..clientes..")
                .should().dependOnClassesThat().resideInAPackage("..barbeiros..");
        rule.check(classes);
    }

    @Test
    @DisplayName("Slice barbeiros não deve depender do slice clientes")
    void barbeirosNaoDeveDependerDeClientes() {
        ArchRule rule = noClasses()
                .that().resideInAPackage("..barbeiros..")
                .should().dependOnClassesThat().resideInAPackage("..clientes..");
        rule.check(classes);
    }

    @Test
    @DisplayName("Slice servicos não deve depender de clientes ou barbeiros")
    void servicosNaoDeveDependerDeOutrosSlices() {
        ArchRule rule = noClasses()
                .that().resideInAPackage("..servicos..")
                .should().dependOnClassesThat()
                .resideInAnyPackage("..clientes..", "..barbeiros..");
        rule.check(classes);
    }

    @Test
    @DisplayName("Infrastructure não deve depender da API")
    void infrastructureNaoDeveDependerDeApi() {
        ArchRule rule = noClasses()
                .that().resideInAPackage("..infrastructure..")
                .should().dependOnClassesThat()
                .resideInAnyPackage("..clientes.api..", "..barbeiros.api..", "..servicos.api..");
        rule.check(classes);
    }
}
