package com.Unimagda.STienda.RepositoryIntegrationTest;

import com.Unimagda.STienda.Entity.Pedido;
import com.Unimagda.STienda.PostgresContainerBase;
import com.Unimagda.STienda.Repository.PedidoRepository;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.testcontainers.junit.jupiter.Testcontainers;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@Testcontainers
@Transactional
public class PedidoRepositoryIntegrationTest extends PostgresContainerBase {
    @Autowired
    private PedidoRepository pedidoRepository;
    @Test
    public void testBusquedaDeClienteID() {
        Pedido pedido = new Pedido();
        List<Pedido> pedidos =pedidoRepository.findByClienteId(1l);
        pedidoRepository.save(pedido);
        assertThat(pedidos).isNotEmpty();
    }
}
