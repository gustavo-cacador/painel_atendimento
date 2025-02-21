package br.com.gustavo.painel_atendimento.services;

import br.com.gustavo.painel_atendimento.dto.ClienteDTO;
import br.com.gustavo.painel_atendimento.entities.Cliente;
import br.com.gustavo.painel_atendimento.repositories.ClienteRepository;
import br.com.gustavo.painel_atendimento.services.exceptions.DatabaseException;
import br.com.gustavo.painel_atendimento.services.exceptions.ResourceNotFoundException;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;
import java.util.List;

@Service
public class ClienteService {

    @Autowired
    private ClienteRepository clienteRepository;

    @Transactional(readOnly = true)
    public ClienteDTO buscarPorId(Long id) {
        Cliente Cliente = clienteRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("Produto com id: " + id + ", não encontrado."));
        return new ClienteDTO(Cliente);
    }

    // Para buscar todos os clientes para ser atendidos
    @Transactional
    public List<ClienteDTO> listarClientesPendentes() {
        List<Cliente> result = clienteRepository.findByChamadaFalseOrderByDataHoraAsc();
        return result.stream()
                .map(x -> new ClienteDTO(x))
                .toList();
    }

    @Transactional
    public List<ClienteDTO> listarClientesAtendidos() {
        List<Cliente> result = clienteRepository.findByChamadaTrue();
        return result.stream()
                .map(x -> new ClienteDTO(x))
                .toList();
    }

    @Transactional
    public ClienteDTO gerarCliente(ClienteDTO dto) {
        Cliente entity = new Cliente();
        entity.setNome(dto.getNome());
        entity.setServico(dto.getServico());
        entity.setSenha(dto.getSenha());
        entity.setMomento(dto.getMomento());
        entity.setChamada(dto.isChamada());


        Long proximoId = clienteRepository.count() + 1;
        entity.setSenha(Integer.valueOf(String.format("%03d", proximoId)));

        entity = clienteRepository.save(entity);
        return new ClienteDTO(entity);
    }



    @Transactional
    public ClienteDTO atualizarCliente(Long id, ClienteDTO dto) {
        try {
            Cliente entity = clienteRepository.getReferenceById(id);
            copyDtoToEntity(dto, entity);
            entity = clienteRepository.save(entity);
            return new ClienteDTO(entity);
        } catch (EntityNotFoundException e) {
            throw new ResourceNotFoundException("Produto com id: " + id + ", não encontrado.");
        }
    }

    @Transactional(propagation = Propagation.SUPPORTS)
    public void deletarCliente(Long id) {
        if (!clienteRepository.existsById(id)){
            throw new ResourceNotFoundException("Produto com id: " + id + ", não encontrado.");
        } try {
            clienteRepository.deleteById(id);
        } catch (DataIntegrityViolationException e) {
            throw new DatabaseException("Falha de integridade referencial");
        }
        clienteRepository.deleteById(id);
    }

    private void copyDtoToEntity(ClienteDTO dto, Cliente entity) {
        entity.setNome(dto.getNome());
        entity.setSenha(dto.getSenha());
    }
}
