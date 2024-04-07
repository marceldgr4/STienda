package com.Unimagda.STienda.RepositoryIntegrationTest;

import com.Unimagda.STienda.Entity.Cliente;
import com.Unimagda.STienda.Repository.ClienteRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
@DataJpaTest
public class ClienteRepositoryIntegrationTest{
@Autowired
private ClienteRepository clienteRepository;
@Autowired
private TestEntityManager entityManager;
@BeforeEach
public void setUp() {
    Cliente cliente = new Cliente("Cliente1","Cliente@exmaple","direccion","Ciudad");
    Cliente cliente2 = new Cliente("Cliente2","Cliente@exmaple","direccion","Ciudad2");
    Cliente cliente3 = new Cliente("Cliente3","Cliente@exmaple","direccion","Ciudad3");
    Cliente cliente4 = new Cliente("Cliente4","Cliente@exmaple","direccion","Ciudad4");
    entityManager.persist(cliente);
    entityManager.persist(cliente2);
    entityManager.persist(cliente3);
    entityManager.persist(cliente4);
    entityManager.flush();
}
@AfterEach
public void tearDown() {
    clienteRepository.deleteAll();
}
@Test
public void testSaveCliente(){
    Cliente cliente = new Cliente("Nuevo Cliente","NuevoCliente@exmaple","direccion","Ciudad");
    Cliente clienteGuardar = clienteRepository.save(cliente);
    assertThat(clienteGuardar).isNotNull();
    assertThat(clienteGuardar.getId()).isNotNull();
    assertThat(clienteGuardar.getNombre()).isEqualTo("Nuevo Cliente");
    assertThat(clienteGuardar.getEmail()).isEqualTo("NuevoCliente@exmaple");
    assertThat(clienteGuardar.getDireccion()).isEqualTo("direccion");
    assertThat(clienteGuardar.getCityName()).isEqualTo("Ciudad");

}
@Test
public void testFindByIdCliente(){
    Cliente clienteEncontrado = clienteRepository.findById(1L).orElse(null);
    assertThat(clienteEncontrado).isNotNull();
    assertThat(clienteEncontrado.getId()).isEqualTo(1L);
    assertThat(clienteEncontrado.getNombre()).isEqualTo("Cliente1");

}
@Test
public void testUpdateCliente(){
    Cliente cliente = clienteRepository.findById(1L).orElse(null);
    assertThat(cliente).isNotNull();

    cliente.setNombre("Nuevo Cliente");
    Cliente clienteUpdate = clienteRepository.save(cliente);
    assertThat(clienteUpdate.getNombre()).isEqualTo("Nuevo Cliente");

}
@Test
public void testDeleteCliente(){
    Cliente cliente = clienteRepository.findById(1L).orElse(null);
    assertThat(cliente).isNotNull();
    clienteRepository.delete(cliente);
    assertThat(clienteRepository.findById(1L)).isEmpty();
}
@Test
public void testFindByEmail(){
    List<Cliente> clienteEncontradoPorEmail = clienteRepository.findByEmail("cliente2@example.com");
    assertThat(clienteEncontradoPorEmail).isNotNull();
    assertThat(clienteEncontradoPorEmail.size()).isEqualTo(1);
    assertThat(clienteEncontradoPorEmail.get(0).getNombre()).isEqualTo("Cliente2");
}
@Test
public  void testFindByDireccion(){
    List<Cliente> ClienteEncontradoPorDireccion = clienteRepository.findByDireccion("direccion");
    assertThat(ClienteEncontradoPorDireccion).isNotNull();
    assertThat(ClienteEncontradoPorDireccion.size()).isEqualTo(1);
    assertThat(ClienteEncontradoPorDireccion.get(0).getNombre()).isEqualTo("Cliente3");
}
@Test
public void testFindByCityName(){
    List<Cliente> clienteEncontradoPorCity = clienteRepository.findByCityName("Ciudad");
    assertThat(clienteEncontradoPorCity).isNotNull();
    assertThat(clienteEncontradoPorCity.size()).isEqualTo(1);
    assertThat(clienteEncontradoPorCity.get(0).getNombre()).isEqualTo("Cliente2");

}
@Test
public void testFindByNombreStartingWith() {
    List<Cliente> clienteEncontradoPorNombre = clienteRepository.findByNombreStartingWith("Cliente3");
    assertThat(clienteEncontradoPorNombre).hasSize(2);
    assertThat(clienteEncontradoPorNombre.get(0).getNombre()).isEqualTo("Cliente3");
    assertThat(clienteEncontradoPorNombre.get(1).getNombre()).isEqualTo("Cliente4");
}

}
