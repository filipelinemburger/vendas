package org.com.br.filipe.vendas;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
public class VendasApplication {


    public static void main(String[] args) {
        SpringApplication.run(VendasApplication.class);
    }

//    @Bean
//    public CommandLineRunner init(@Autowired ClienteRepository clienteRepository, @Autowired PedidoRepository pedidoRepository) {
//        return args -> {
//
//            clienteRepository.save(new Cliente("Cliente 1"));
//            clienteRepository.save(new Cliente("Cliente 2"));
//
//            Cliente maria = new Cliente("Cliente 3");
//            clienteRepository.save(maria);
//        };
//    }
}
