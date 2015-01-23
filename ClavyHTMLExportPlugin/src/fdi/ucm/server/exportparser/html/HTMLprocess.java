/**
 * 
 */
package fdi.ucm.server.exportparser.html;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.URI;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import javax.imageio.ImageIO;
import javax.management.RuntimeErrorException;

import fdi.ucm.server.modelComplete.collection.CompleteCollection;
import fdi.ucm.server.modelComplete.collection.CompleteLogAndUpdates;
import fdi.ucm.server.modelComplete.collection.document.CompleteDocuments;
import fdi.ucm.server.modelComplete.collection.document.CompleteElement;
import fdi.ucm.server.modelComplete.collection.document.CompleteFile;
import fdi.ucm.server.modelComplete.collection.document.CompleteLinkElement;
import fdi.ucm.server.modelComplete.collection.document.CompleteResourceElementFile;
import fdi.ucm.server.modelComplete.collection.document.CompleteResourceElementURL;
import fdi.ucm.server.modelComplete.collection.document.CompleteTextElement;
import fdi.ucm.server.modelComplete.collection.grammar.CompleteElementType;
import fdi.ucm.server.modelComplete.collection.grammar.CompleteGrammar;
import fdi.ucm.server.modelComplete.collection.grammar.CompleteIterator;
import fdi.ucm.server.modelComplete.collection.grammar.CompleteStructure;

/**
 * @author Joaquin Gayoso-Cabada
 *
 */
public class HTMLprocess {

	protected static final String EXPORTTEXT = "Export HTML RESULT";
	protected ArrayList<Long> ListaDeDocumentos;
	protected CompleteCollection Salvar;
	protected String SOURCE_FOLDER;
	protected StringBuffer CodigoHTML;
	protected CompleteLogAndUpdates CL;

	public HTMLprocess(ArrayList<Long> listaDeDocumentos, CompleteCollection salvar, String sOURCE_FOLDER, CompleteLogAndUpdates cL) {
		ListaDeDocumentos=listaDeDocumentos;
		Salvar=salvar;
		SOURCE_FOLDER=sOURCE_FOLDER;
		CL=cL;
		
	}

	public void preocess() {
		CodigoHTML=new StringBuffer();
		CodigoHTML.append("<html>");
		CodigoHTML.append("<head>");  
		CodigoHTML.append("<title>"+EXPORTTEXT+"</title><meta http-equiv=\"Content-Type\" content=\"text/html; charset=utf-8\">"); 
		CodigoHTML.append("<style>");
		CodigoHTML.append("li.doc {color: blue;}");	
		CodigoHTML.append("</style>");
		CodigoHTML.append("</head>");  
		CodigoHTML.append("<body>");
		CodigoHTML.append("<ul>");
		
		
		ArrayList<CompleteGrammar> GramaticasAProcesar=ProcesaGramaticas(Salvar.getMetamodelGrammar());
		for (CompleteGrammar completeGrammar : GramaticasAProcesar) {
			ArrayList<CompleteDocuments> Lista=calculadocumentos(completeGrammar);
			proceraDocumentos(Lista,completeGrammar);
		}
		
		CodigoHTML.append("</ul>");
		CodigoHTML.append("</body>");
		CodigoHTML.append("</html>");
		
		creaLaWeb();
		
		
		
	}

	private void creaLaWeb() {
		 FileWriter filewriter = null;
		 PrintWriter printw = null;
		    
		try {
			 filewriter = new FileWriter(SOURCE_FOLDER+"\\index.html");//declarar el archivo
		     printw = new PrintWriter(filewriter);//declarar un impresor
		          
		     printw.println(CodigoHTML.toString());
		     
		     printw.close();//cerramos el archivo
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeErrorException(new Error(e), "Error de archivo");
		} 
		 
		            

		
	}

	protected void proceraDocumentos(ArrayList<CompleteDocuments> lista,
			CompleteGrammar completeGrammar) {
		for (CompleteDocuments completeDocuments : lista) {
			CodigoHTML.append("<li class=\"doc\"><b> Document: "+completeDocuments.getClavilenoid()+"<b></li>");
			CodigoHTML.append("<ul>");
			File IconF=new File(SOURCE_FOLDER+File.separator+completeDocuments.getClavilenoid());
			IconF.mkdirs();
			
			
			String Path=StaticFunctionsHTML.calculaIconoString(completeDocuments.getIcon());
			
			String[] spliteStri=Path.split("/");
			String NameS = spliteStri[spliteStri.length-1];
			String Icon=SOURCE_FOLDER+File.separator+completeDocuments.getClavilenoid()+File.separator+NameS;
			
			try {
				URL url2 = new URL(Path);
				 URI uri2 = new URI(url2.getProtocol(), url2.getUserInfo(), url2.getHost(), url2.getPort(), url2.getPath(), url2.getQuery(), url2.getRef());
				 url2 = uri2.toURL();
				
				saveImage(url2, Icon);
			} catch (Exception e) {
				CL.getLogLines().add("Error in Icon copy, file with url ->>"+completeDocuments.getIcon()+" not found or restringed");
			}
			
			int width= 50;
			int height=50;
			int widthmini= 50;
			int heightmini=50;
			
			try {
				BufferedImage bimg = ImageIO.read(new File(SOURCE_FOLDER+File.separator+completeDocuments.getClavilenoid()+File.separator+NameS));
				width= bimg.getWidth();
				height= bimg.getHeight();
			} catch (Exception e) {
				e.printStackTrace();
			}
			
			
			 widthmini= 50;
			 heightmini= (50*height)/width;
			
//			if (width=0)
			
			
			CodigoHTML.append("<li> <b>Icon:</b> <img src=\""+completeDocuments.getClavilenoid()+File.separator+NameS+"\" onmouseover=\"this.width="+width+";this.height="+height+";\" onmouseout=\"this.width="+widthmini+";this.height="+heightmini+";\" width=\""+widthmini+"\" height=\""+heightmini+"\" alt=\""+Path+"\" /></li>");
			CodigoHTML.append("<li> <b>Description:</b> "+completeDocuments.getDescriptionText()+"</li>");
			for (CompleteStructure completeST : completeGrammar.getSons()) {
				String Salida = processST(completeST,completeDocuments,new ArrayList<Integer>());
				if (!Salida.isEmpty())
					CodigoHTML.append(Salida);
			}
			
			CodigoHTML.append("</ul>");
			CodigoHTML.append("<br>");
		}
		
		
	}
	
	/**
	 * Salva una imagen dado un destino
	 * @param imageUrl
	 * @param destinationFile
	 * @throws IOException
	 */
	protected void saveImage(URL imageUrl, String destinationFile) throws IOException {

		URL url = imageUrl;
		InputStream is = url.openStream();
		OutputStream os = new FileOutputStream(destinationFile);

		byte[] b = new byte[2048];
		int length;

		while ((length = is.read(b)) != -1) {
			os.write(b, 0, length);
		}

		is.close();
		os.close();
	}

	private String processST(CompleteStructure completeST,
			CompleteDocuments completeDocuments, ArrayList<Integer> ambitos) {
		StringBuffer StringSalida=new StringBuffer();
		boolean Vacio=true;
		if (completeST instanceof CompleteElementType)
			{
			CompleteElement E=findElem(completeST,completeDocuments.getDescription(),ambitos);
			if (E!=null)
				{
				Vacio=false;
				if (E instanceof CompleteTextElement)
					StringSalida.append("<li> <b>"+((CompleteElementType)completeST).getName()+":</b> "+((CompleteTextElement)E).getValue()+"</li>");
				else if (E instanceof CompleteLinkElement)
					{
					CompleteDocuments Linked=((CompleteLinkElement) E).getValue();
					
					File IconF=new File(SOURCE_FOLDER+File.separator+completeDocuments.getClavilenoid());
					IconF.mkdirs();
					
					
					String Path=StaticFunctionsHTML.calculaIconoString(Linked.getIcon());
					
					String[] spliteStri=Path.split("/");
					String NameS = spliteStri[spliteStri.length-1];
					String Icon=SOURCE_FOLDER+File.separator+completeDocuments.getClavilenoid()+File.separator+NameS;
					
					try {
						URL url2 = new URL(Path);
						 URI uri2 = new URI(url2.getProtocol(), url2.getUserInfo(), url2.getHost(), url2.getPort(), url2.getPath(), url2.getQuery(), url2.getRef());
						 url2 = uri2.toURL();
						
						saveImage(url2, Icon);
					} catch (Exception e) {
						CL.getLogLines().add("Error in Icon copy, file with url ->>"+Linked.getIcon()+" not found or restringed");
					}
					
					
					
					int width= 50;
					int height=50;
					int widthmini= 50;
					int heightmini=50;
					
					try {
						BufferedImage bimg = ImageIO.read(new File(SOURCE_FOLDER+File.separator+completeDocuments.getClavilenoid()+File.separator+NameS));
						width= bimg.getWidth();
						height= bimg.getHeight();
					} catch (Exception e) {
						e.printStackTrace();
					}
					
					
					 widthmini= 50;
					 heightmini= (50*height)/width;
					
					
					StringSalida.append("<li> <b>"+((CompleteElementType)completeST).getName()+": </b> Document Linked -> <img src=\""+completeDocuments.getClavilenoid()+File.separator+NameS+"\" onmouseover=\"this.width="+width+";this.height="+height+";\" onmouseout=\"this.width="+widthmini+";this.height="+heightmini+";\" width=\""+widthmini+"\" height=\""+heightmini+"\" alt=\""+Path+"\" /> "+Linked.getDescriptionText()+"</li>");
					}
				else if (E instanceof CompleteResourceElementURL)
					StringSalida.append("<li> <b>"+((CompleteElementType)completeST).getName()+": </b>"+((CompleteResourceElementURL)E).getValue()+"</li>");
				else if (E instanceof CompleteResourceElementFile)
					{
					CompleteFile Linked=((CompleteResourceElementFile) E).getValue();
					
					
					File IconF=new File(SOURCE_FOLDER+File.separator+completeDocuments.getClavilenoid());
					IconF.mkdirs();
					
					String Path=StaticFunctionsHTML.calculaIconoString(Linked.getPath());
					
					
					String[] spliteStri=Path.split("/");
					String NameS = spliteStri[spliteStri.length-1];
					String Icon=SOURCE_FOLDER+File.separator+completeDocuments.getClavilenoid()+File.separator+NameS;
					
					try {
						URL url2 = new URL(Path);
						 URI uri2 = new URI(url2.getProtocol(), url2.getUserInfo(), url2.getHost(), url2.getPort(), url2.getPath(), url2.getQuery(), url2.getRef());
						 url2 = uri2.toURL();
						
						saveImage(url2, Icon);
					} catch (Exception e) {
						CL.getLogLines().add("Error in Icon copy, file with url ->> "+Linked.getPath()+" not found or restringed");
					}
					
					int width= 50;
					int height=50;
					int widthmini= 50;
					int heightmini=50;
					
					try {
						BufferedImage bimg = ImageIO.read(new File(SOURCE_FOLDER+File.separator+completeDocuments.getClavilenoid()+File.separator+NameS));
						width= bimg.getWidth();
						height= bimg.getHeight();
					} catch (Exception e) {
						e.printStackTrace();
					}
					
					
					 widthmini= 50;
					 heightmini= (50*height)/width;
					
					StringSalida.append("<li> <b>"+((CompleteElementType)completeST).getName()+":</b> File Linked -> <img src=\""+completeDocuments.getClavilenoid()+File.separator+NameS+"\" onmouseover=\"this.width="+width+";this.height="+height+";\" onmouseout=\"this.width="+widthmini+";this.height="+heightmini+";\" width=\""+widthmini+"\" height=\""+heightmini+"\" alt=\""+Path+"\" /></li>");
					}
				else 
					Vacio=true;
				
				}
			else
				Vacio=true;
			
			StringBuffer Hijos=new StringBuffer();
			for (CompleteStructure hijo : completeST.getSons()) {
				
				String result2 = processST(hijo, completeDocuments, ambitos);
				
				if (!result2.isEmpty())
					Hijos.append(result2.toString());	
			}
			
			
			String HijosSalida = Hijos.toString();
			
			if (!HijosSalida.isEmpty()&&Vacio)
			{
			StringSalida.append("<li> <b>"+((CompleteElementType)completeST).getName()+":</b> </li>");
			
			}
		
		if (!HijosSalida.isEmpty())
			{
			StringSalida.append("<ul>");
			StringSalida.append(HijosSalida);
			StringSalida.append("</ul>");
			}
			
			}
		else
			if (completeST instanceof CompleteIterator)
			{
				HashSet<Integer> AmbitosViables=calculaAmbitos(ambitos,completeST,completeDocuments);
			for (Integer integer : AmbitosViables) {
				ArrayList<Integer> ambitosNuevos=new ArrayList<Integer>();
				for (Integer integer2 : ambitos) 
					ambitosNuevos.add(integer2.intValue());
				ambitosNuevos.add(integer);
				
				StringBuffer Hijos=new StringBuffer();
				for (CompleteStructure hijo : completeST.getSons()) {
					
					String result2 = processST(hijo, completeDocuments, ambitosNuevos);
					
					if (!result2.isEmpty())
						Hijos.append(result2.toString());	
				}
				
				String HijosSalida = Hijos.toString();
				
				if (!HijosSalida.isEmpty())
				{

				StringSalida.append(HijosSalida);

				}
			}	
			}
		
		
		
		return StringSalida.toString();
		
	}



	protected HashSet<Integer> calculaAmbitos(ArrayList<Integer> ambitos,
			CompleteStructure completeST, CompleteDocuments completeDocuments) {
		HashSet<Long> hijos=new HashSet<Long>();
		calculaHijos(completeST,hijos);
		HashSet<Integer> Salida=new HashSet<Integer>();
		int ultimo=ambitos.size();
		for (CompleteElement element : completeDocuments.getDescription()) {
			if (hijos.contains(element.getHastype().getClavilenoid())&&element.getAmbitos().size()>ultimo)
				if (!Salida.contains(element.getAmbitos().get(ultimo)))
					Salida.add(element.getAmbitos().get(ultimo));
				
				
		}
		return Salida;
	}

	private void calculaHijos(CompleteStructure completeST, HashSet<Long> hijos) {
		if (!hijos.contains(completeST.getClavilenoid()))
			hijos.add(completeST.getClavilenoid());
		for (CompleteStructure hijo : completeST.getSons()) {
			calculaHijos(hijo,hijos);
		}
	}

	protected CompleteElement findElem(CompleteStructure completeST, List<CompleteElement> description,
			ArrayList<Integer> ambitos) {
		for (CompleteElement elementos : description) {
			if (elementos.getHastype().getClavilenoid().equals(completeST.getClavilenoid())&&validos(elementos.getAmbitos(),ambitos))
				return elementos;
		}
		return null;
	}

	private boolean validos(ArrayList<Integer> documento,
			ArrayList<Integer> actual) {
		if (actual.size()>documento.size())
			return false;
		
		for (int i = 0; i < actual.size(); i++) {
			if (!actual.get(i).equals(documento.get(i)))
				return false;
		}
		
		return true;
	}

	protected ArrayList<CompleteDocuments> calculadocumentos(
			CompleteGrammar completeGrammar) {
		ArrayList<CompleteDocuments> Salida=new ArrayList<CompleteDocuments>();
		for (CompleteDocuments iterable_element : Salvar.getEstructuras()) {
			if (ListaDeDocumentos.contains(iterable_element.getClavilenoid())&&iterable_element.getDocument().getClavilenoid().equals(completeGrammar.getClavilenoid()))
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
