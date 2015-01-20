/**
 * 
 */
package fdi.ucm.server.exportparser.html.oda;

import java.util.ArrayList;

import fdi.ucm.server.exportparser.html.HTMLprocess;
import fdi.ucm.server.modelComplete.collection.CompleteCollection;

/**
 * @author Joaquin Gayoso-Cabada
 *
 */
public class HTMLprocessOdA extends HTMLprocess {

	private ArrayList<Long> AdministradorListaDocumentos;
	private boolean Administrador;

	/**
	 * @param listaDeDocumentos
	 * @param salvar 
	 * @param b 
	 * @param arrayList 
	 */
	public HTMLprocessOdA(ArrayList<Long> listaDeDocumentos, CompleteCollection salvar, ArrayList<Long> administradorList, boolean administrador) {
		super(listaDeDocumentos,salvar);
		AdministradorListaDocumentos=administradorList;
		Administrador=administrador;
	}

}
