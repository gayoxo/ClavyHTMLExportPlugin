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
	 * @param sOURCE_FOLDER 
	 * @param b 
	 * @param arrayList 
	 */
	public HTMLprocessOdA(ArrayList<Long> listaDeDocumentos, CompleteCollection salvar, String sOURCE_FOLDER, ArrayList<Long> administradorList, boolean administrador) {
		super(listaDeDocumentos,salvar,sOURCE_FOLDER);
		AdministradorListaDocumentos=administradorList;
		Administrador=administrador;
	}

}
