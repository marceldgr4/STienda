package com.Unimagda.STienda.RepositoryTest;

import com.Unimagda.STienda.AbstractIntegrationBDTest;
import com.Unimagda.STienda.Entity.Cliente;
import com.Unimagda.STienda.Repository.Repositorys.ClienteRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import java.awt.print.Pageable;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;


public class ClienteRepositoryTest extends AbstractIntegrationBDTest {

    private final  ClienteRepository clienteRepository;
    @Autowired
    public ClienteRepositoryTest(ClienteRepository clienteRepository) {
        this.clienteRepository = clienteRepository;
    }
    @BeforeEach

    public void setUp() {
        clienteRepository.deleteAll();
    }
    void CrearCliente() {
        Cliente cliente = Cliente.builder()
                .Nombre("cliente")
                .Email("clientexample@email.com")
                .Direccion("direccion")
                .CityName("Ciudad1")
                .build();
        clienteRepository.save(cliente);

        Cliente cliente2 = Cliente.builder()
                .Nombre("cliente2")
                .Email("cliente2xample@email.com")
                .Direccion("direccion")
                .CityName("Ciudad2")
                .build();
        clienteRepository.save(cliente2);
        clienteRepository.flush();

    }
    @Test
    public void dadoCliente_cuandoGuardar_entoncesIdClienteNoNulo(){
        CrearCliente();
        Cliente cliente =clienteRepository.findAll().get(0);
        assertNotNull(cliente.getId());
    }
    @Test
    public void dadoEmail_cuandoBuscarPorEmail_entoncesClienteRetornado(){
        CrearCliente();
        List<Cliente> cliente = clienteRepository.findAll();
        List<Cliente> clientes = clienteRepository.findByEmail("cliente@email.com");
        assertNotNull(cliente);
        assertEquals(clientes.get(0).getEmail(),"cliente@email.com");

    }
    @Test
   public void dadoDireccion_cuandoBuscarPorDireccion_entoncesClienteRetornado(){
        CrearCliente();
        List<Cliente> cliente = clienteRepository.findAll();
        List<Cliente> clientes = clienteRepository.findByDireccion("direccion");
        assertNotNull(cliente);
        assertEquals(clientes.get(0).getDireccion(),"direccion");
    }
    @Test
    public void dadoNombre_cuandoBuscarPorNombreQueEmpiezaCon_entoncesListaVacia(){
        CrearCliente();
        Pageable pageable = (Pageable) PageRequest.of(0,10);
        Page<Cliente> clientes = (Page<Cliente>) clienteRepository.findByNombreStartingWith(PageRequest.of(0, 10), "cliente3");
        assertThat(clientes).isEmpty();
    }
    @Test
   public void dadoIdCliente_cuandoBuscarPorIdCliente_entoncesClienteRetornado(){
        CrearCliente();
        Long idCliente=1l;
        Cliente cliente = clienteRepository.findById(idCliente).get();
        assertEquals("cliente",cliente.getNombre());
    }
    @Test
   public void dadoIdCliente_cuandoEliminarPorId_entoncesClienteEliminado(){
        CrearCliente();
        Long idCliente = 1l;
        clienteRepository.deleteById(idCliente);
        assertEquals(1,clienteRepository.findAll().size());


    }




}
