package com.github.arthurmatosbsb.rest;

import javax.inject.Inject;
import javax.validation.Valid;
import javax.validation.Validator;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.eclipse.microprofile.openapi.annotations.Operation;
import org.eclipse.microprofile.openapi.annotations.enums.SchemaType;
import org.eclipse.microprofile.openapi.annotations.media.Content;
import org.eclipse.microprofile.openapi.annotations.media.Schema;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponse;

import com.github.arthurmatosbsb.DAO.ClienteDAO;
import com.github.arthurmatosbsb.entity.Cliente;

@Path("/cliente")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class ClienteRest {

	@Inject
	ClienteDAO dao;

	@Inject
	Validator validator;

	@GET
	@Path("")
	@Operation(summary = "Lista de Clientes", description = "Retorna uma lista de  Cliente.class")
	@APIResponse(responseCode = "200", description = "Lista de Clientes", content = {
			@Content(mediaType = "application/json", schema = @Schema(implementation = Cliente.class, type = SchemaType.ARRAY)) })
	public Response listar() {
		return Response.status(Response.Status.OK).entity(dao.listarTudo()).build();
	}

	@GET
	@Path("/{id}")
	@Operation(summary = "Buscar cliente por ID",
	description = "Buscar cliente  por ID")
	@APIResponse(responseCode = "200",
	description = "Cliente",
	content = {
			@Content(mediaType =  "application/json",
			schema = @Schema(implementation = Cliente.class))
			}
	)
	public Response listarPorID(@PathParam("id") Integer id) {

		return Response.status(Response.Status.OK).entity(dao.listarPorId(id)).build();
	}

	@POST
	@Path("")
	@Operation(summary = "Cadastrar cliente",
	description = "Incluir cliente")
	@APIResponse(responseCode = "201",
	description = "Cliente",
	content = {
			@Content(mediaType =  "application/json",
			schema = @Schema(implementation = Cliente.class))
			}
	)
	public Response cadastrar(@Valid Cliente cliente) {
		dao.inserir(cliente);
		return Response.status(Response.Status.CREATED).build();
	}

	@PUT
	@Path("/{id}")
	@Operation(summary = "Edtiar cliente pelo ID",
	description = "Edtiar cliente pelo ID")
	@APIResponse(responseCode = "200",
	description = "Cliente",
	content = {
			@Content(mediaType =  "application/json",
			schema = @Schema(implementation = Cliente.class))
			}
	)
	public Response atualizar(@PathParam("id") Integer id, Cliente cliente) {
		dao.atualizar(id, cliente);
		return Response.status(Response.Status.OK).build();
	}

	@DELETE
	@Path("/{id}")
	@Operation(summary = "Excluir cliente",
	description = "Excluir cliente")
	@APIResponse(responseCode = "202",
	description = "Cliente",
	content = {
			@Content(mediaType =  "application/json",
			schema = @Schema(implementation = Cliente.class))
			}
	)
	public Response delete(@PathParam("id") Integer id) {
		dao.remove(id);
		return Response.status(Response.Status.ACCEPTED).build();
	}
}
