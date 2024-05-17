package com.Unimagda.STienda.RepositoryTest;

import com.Unimagda.STienda.AbstractIntegrationBDTest;
import com.Unimagda.STienda.Entity.Cliente;
import com.Unimagda.STienda.Repository.Repositorys.ClienteRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;


public class ClienteRepositoryTest extends AbstractIntegrationBDTest {
    private final ClienteRepository clienteRepository;
    @Autowired
    public ClienteRepositoryTest(ClienteRepository clienteRepository) {
        this.clienteRepository = clienteRepository;
    }
    @BeforeEach
    void setUp() {
        clienteRepository.deleteAll();
    }
    void createCliente() {
        Cliente cliente = Cliente.builder()
                .Nombre("cliente2")
                .Email("cliente2@gmail.com")
                .Direccion("direccion2")
                .CityName("ciudad2")
                .build();
        clienteRepository.save(cliente);
        Cliente cliente1 = Cliente.builder()
                .Nombre("cliente1")
                .Email("cliente1@gmail.com")
                .Direccion("direccion1")
                .CityName("ciudad1")
                .build();
        clienteRepository.save(cliente1);
        clienteRepository.flush();
    }
    @Test
    void dado_cliente_cuando_guarda_el_idCliente(){
        createCliente();
        Cliente cliente = clienteRepository.findAll().get(0);
        assertNotNull(cliente.getIdCliente());
    }
    @Test
    void dado_Email_cuando_se_busca_por_Email_regresa_Cliente(){
        createCliente();
        List<Cliente> clienteList =clienteRepository.findAll();
        Cliente cliente = clienteRepository.findByEmail("cliente2@gmail.com");
        assertNotNull(cliente);
        assertEquals(clienteList.get(0).getEmail(),cliente.getEmail());
    }
    @Test
    void dado_Direccion_cuando_se_busca_por_Direccion_regresaCliente(){
        createCliente();
        List<Cliente> clienteList =clienteRepository.findAll();
        Cliente cliente = clienteRepository.findByDireccion("Direccion2");
        assertNotNull(cliente);
        assertEquals(clienteList.get(0).getDireccion(),cliente.getDireccion());
    }
    @Test
    void dadoCityName_cuando_se_busca_por_CityName_regresaCliente(){
        createCliente();
        List<Cliente>clienteList = clienteRepository.findAll();
        Cliente cliente= clienteRepository.findByCityName("Ciudad1");
        assertNotNull(cliente);
        assertEquals(clienteList.get(0).getCityName(),cliente.getCityName());
    }
    @Test
    void dado_un_Nombre_cuando_busca_el_nombre_comienzo_regresaCliente(){
        createCliente();
        Pageable pageable = PageRequest.of(0,10);
        List<Cliente> clienteList = clienteRepository.findAll();
        Cliente cliente = clienteRepository.findByNombreStartingWith(pageable,"cliente")
                .getContent()
                .get(0);
        assertNotNull(cliente);
        assertEquals(clienteList.get(0).getNombre(),cliente.getNombre());
    }
    @Test
    void dado_idCliente_cuando_busca_porIDCliente_regresaCliente(){
        createCliente();
        Long idCliente=1L;
        Cliente cliente = clienteRepository.findById(idCliente).get();
        assertEquals("cliente",cliente.getNombre());
    }
    @Test
    void dado_idCliente_cuandoElimina_ID_regresaClienteEliminado(){
        Long idCliente = 1L;
        clienteRepository.deleteById(idCliente);
        assertEquals(1,clienteRepository.findAll().size());
    }
}
