/**
 * 
 */
package fdi.ucm.server.exportparser.html;

import java.util.ArrayList;
import java.util.List;

import fdi.ucm.server.modelComplete.collection.CompleteCollection;
import fdi.ucm.server.modelComplete.collection.document.CompleteDocuments;
import fdi.ucm.server.modelComplete.collection.grammar.CompleteGrammar;

/**
 * @author Joaquin Gayoso-Cabada
 *
 */
public class HTMLprocess {

	private ArrayList<Long> ListaDeDocumentos;
	private CompleteCollection Salvar;
	private String SOURCE_FOLDER;

	public HTMLprocess(ArrayList<Long> listaDeDocumentos, CompleteCollection salvar, String sOURCE_FOLDER) {
		ListaDeDocumentos=listaDeDocumentos;
		Salvar=salvar;
		SOURCE_FOLDER=sOURCE_FOLDER;
	}

	public void preocess() {
		ArrayList<CompleteGrammar> GramaticasAProcesar=ProcesaGramaticas(Salvar.getMetamodelGrammar());
		for (CompleteGrammar completeGrammar : GramaticasAProcesar) {
			ArrayList<CompleteDocuments> Lista=calculadocumentos(completeGrammar);
			proceraDocumentos(Lista,completeGrammar);
		}
	}

	protected void proceraDocumentos(ArrayList<CompleteDocuments> lista,
			CompleteGrammar completeGrammar) {
		// TODO Auto-generated method stub
		
	}

	protected ArrayList<CompleteDocuments> calculadocumentos(
			CompleteGrammar completeGrammar) {
		ArrayList<CompleteDocuments> Salida=new ArrayList<CompleteDocuments>();
		for (CompleteDocuments iterable_element : Salvar.getEstructuras()) {
			if (ListaDeDocumentos.contains(iterable_element.getClavilenoid()))
				Salida.add(iterable_element);
		}
		return Salida;
	}

	protected ArrayList<CompleteGrammar> ProcesaGramaticas(
			List<CompleteGrammar> metamodelGrammar) {
		ArrayList<CompleteGrammar> Salida=new ArrayList<CompleteGrammar>();
		for (CompleteGrammar completeGrammar : metamodelGrammar) {
			Salida.add(completeGrammar);
		}
		return Salida;
	}

}
