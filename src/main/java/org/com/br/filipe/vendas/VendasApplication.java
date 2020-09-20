package org.com.br.filipe.vendas;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
public class VendasApplication {


    public static void main(String[] args) {
        SpringApplication.run(VendasApplication.class);
    }

//    @Bean
//    public CommandLineRunner init(@Autowired ClienteRepository clienteRepository, @Autowired PedidoRepository pedidoRepository) {
//        return args -> {
//
//            clienteRepository.save(new Cliente("Filipe"));
//            clienteRepository.save(new Cliente("Rafaela"));
//
//            Cliente maria = new Cliente("Maria");
//            clienteRepository.save(maria);
//
//
//        };
//    }
}
