/**
 * 
 */
package fdi.ucm.server.exportparser.html.oda;

import java.awt.image.BufferedImage;
import java.io.File;
import java.net.URI;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import javax.imageio.ImageIO;

import fdi.ucm.server.exportparser.html.HTMLprocess;
import fdi.ucm.server.exportparser.html.StaticFunctionsHTML;
import fdi.ucm.server.modelComplete.collection.CompleteCollection;
import fdi.ucm.server.modelComplete.collection.CompleteLogAndUpdates;
import fdi.ucm.server.modelComplete.collection.document.CompleteDocuments;
import fdi.ucm.server.modelComplete.collection.document.CompleteElement;
import fdi.ucm.server.modelComplete.collection.document.CompleteLinkElement;
import fdi.ucm.server.modelComplete.collection.document.CompleteResourceElement;
import fdi.ucm.server.modelComplete.collection.document.CompleteResourceElementFile;
import fdi.ucm.server.modelComplete.collection.document.CompleteResourceElementURL;
import fdi.ucm.server.modelComplete.collection.document.CompleteTextElement;
import fdi.ucm.server.modelComplete.collection.grammar.CompleteElementType;
import fdi.ucm.server.modelComplete.collection.grammar.CompleteGrammar;
import fdi.ucm.server.modelComplete.collection.grammar.CompleteIterator;
import fdi.ucm.server.modelComplete.collection.grammar.CompleteResourceElementType;
import fdi.ucm.server.modelComplete.collection.grammar.CompleteStructure;
import fdi.ucm.server.modelComplete.collection.grammar.CompleteTextElementType;

/**
 * @author Joaquin Gayoso-Cabada
 *
 */
public class HTMLprocessOdA extends HTMLprocess {

	private HashSet<Long> AdministradorListaDocumentos;
	private boolean Administrador;

	/**
	 * @param listaDeDocumentos
	 * @param salvar 
	 * @param sOURCE_FOLDER 
	 * @param cL 
	 * @param b 
	 * @param arrayList 
	 */
	public HTMLprocessOdA(ArrayList<Long> listaDeDocumentos, CompleteCollection salvar, String sOURCE_FOLDER, CompleteLogAndUpdates cL, ArrayList<Long> administradorList, boolean administrador) {
		super(listaDeDocumentos,salvar,sOURCE_FOLDER,cL);
		AdministradorListaDocumentos=new HashSet<Long>();
		for (Long long1 : administradorList) {
			if (!AdministradorListaDocumentos.contains(long1))
				AdministradorListaDocumentos.add(long1);
		}
		Administrador=administrador;
	}
	
	@Override
	protected ArrayList<CompleteGrammar> ProcesaGramaticas(
			List<CompleteGrammar> metamodelGrammar) {
		ArrayList<CompleteGrammar> Salida=new ArrayList<CompleteGrammar>();
		for (CompleteGrammar completeGrammar : metamodelGrammar) {
			if (StaticFuctionsHTMLOdA.isVirtualObject(completeGrammar))
				Salida.add(completeGrammar);
		}
		return Salida;
	}
	
	@Override
	protected ArrayList<CompleteDocuments> calculadocumentos(
			CompleteGrammar completeGrammar) {
		ArrayList<CompleteDocuments> Salida1=new ArrayList<CompleteDocuments>();
		for (CompleteDocuments iterable_element : Salvar.getEstructuras()) {
			if (inListNormal(iterable_element)&&iterable_element.getDocument().getClavilenoid().equals(completeGrammar.getClavilenoid()))
				Salida1.add(iterable_element);
		}
		
		ArrayList<CompleteDocuments> Salida=new ArrayList<CompleteDocuments>();
		for (CompleteDocuments completeDocuments : Salida1) {
			if (StaticFuctionsHTMLOdA.getPublic(completeDocuments)||Administrador||inList(completeDocuments))
				Salida.add(completeDocuments);
		}
		
		
		return Salida;
	}
	
	private boolean inListNormal(CompleteDocuments completeDocuments) {
		String IDOV=completeDocuments.getClavilenoid()+"";
		for (CompleteElement elemetpos : completeDocuments.getDescription()) {
			if (elemetpos instanceof CompleteTextElement&&elemetpos.getHastype() instanceof CompleteTextElementType&&StaticFuctionsHTMLOdA.isIDOV((CompleteTextElementType)elemetpos.getHastype()))
				IDOV=((CompleteTextElement) elemetpos).getValue();
		}
		
		try {
			Long IDOVL=Long.parseLong(IDOV);
			if (ListaDeDocumentos.contains(IDOVL))
				return true;
			else 
				return false;
		} catch (Exception e) {
			return false;
		}
		
		
	}
	
	private boolean inList(CompleteDocuments completeDocuments) {
		String IDOV=completeDocuments.getClavilenoid()+"";
		for (CompleteElement elemetpos : completeDocuments.getDescription()) {
			if (elemetpos instanceof CompleteTextElement&&elemetpos.getHastype() instanceof CompleteTextElementType&&StaticFuctionsHTMLOdA.isIDOV((CompleteTextElementType)elemetpos.getHastype()))
				IDOV=((CompleteTextElement) elemetpos).getValue();
		}
		
		try {
			Long IDOVL=Long.parseLong(IDOV);
			if (AdministradorListaDocumentos.contains(IDOVL))
				return true;
			else 
				return false;
		} catch (Exception e) {
			return false;
		}
		
		
	}

	@Override
	protected void proceraDocumentos(ArrayList<CompleteDocuments> lista,
			CompleteGrammar completeGrammar) {
		for (CompleteDocuments completeDocuments : lista) {
			
			String IDOV=completeDocuments.getClavilenoid()+"";
			for (CompleteElement elemetpos : completeDocuments.getDescription()) {
				if (elemetpos instanceof CompleteTextElement&&elemetpos.getHastype() instanceof CompleteTextElementType&&StaticFuctionsHTMLOdA.isIDOV((CompleteTextElementType)elemetpos.getHastype()))
					IDOV=((CompleteTextElement) elemetpos).getValue();
			}
			CodigoHTML.append("<li> Document: "+IDOV+"</li>");
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
			
			
			CodigoHTML.append("<li> Icon: <img src=\""+completeDocuments.getClavilenoid()+File.separator+NameS+"\" onmouseover=\"this.width="+width+";this.height="+height+";\" onmouseout=\"this.width="+widthmini+";this.height="+heightmini+";\" width=\""+widthmini+"\" height=\""+heightmini+"\" alt=\""+Path+"\" /></li>");
			CodigoHTML.append("<li> Description: "+completeDocuments.getDescriptionText()+"</li>");
			
			
			ArrayList<CompleteStructure> OdAElements=findOdAElements(completeGrammar.getSons());
			
			
			for (CompleteStructure completeST : OdAElements) {
				String Salida="";
				if (completeST instanceof CompleteElementType&&(StaticFuctionsHTMLOdA.isDatos((CompleteElementType)completeST)||StaticFuctionsHTMLOdA.isMetaDatos((CompleteElementType)completeST)))
						Salida = processSTDatosYMeta(completeST,completeDocuments,new ArrayList<Integer>());
				else if (completeST instanceof CompleteElementType&&StaticFuctionsHTMLOdA.isResources((CompleteElementType)completeST))
						{
						HashSet<Integer> AmbitosViables=calculaAmbitos(new ArrayList<Integer>(),completeST,completeDocuments);
						StringBuffer StringSalida=new StringBuffer();
						for (Integer integer : AmbitosViables) {
							ArrayList<Integer> ambitosNuevos=new ArrayList<Integer>();
							ambitosNuevos.add(integer);
							
							StringBuffer Hijos=new StringBuffer();


								String result2 = processSTRecursos(completeST, completeDocuments, ambitosNuevos);
								
								if (!result2.isEmpty())
									Hijos.append(result2.toString());	

							String HijosSalida = Hijos.toString();
							
							if (!HijosSalida.isEmpty())
							{

							StringSalida.append(HijosSalida);

							}
						}
						String ST = StringSalida.toString();
						StringBuffer StringSalidaFinal = new StringBuffer();
						if (!ST.isEmpty())
							{
							StringSalidaFinal.append("<li> "+((CompleteElementType)completeST).getName()+": </li>");
							StringSalidaFinal.append("<ul>");
							Salida = StringSalida.toString();
							StringSalidaFinal.append("</ul>");
							}
						}
				if (!Salida.isEmpty())
					CodigoHTML.append(Salida);
					
			}
			
			CodigoHTML.append("</ul>");
			
		}
		
		
	}

	private String processSTDatosYMeta(CompleteStructure completeST,
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
					StringSalida.append("<li> "+((CompleteElementType)completeST).getName()+": "+((CompleteTextElement)E).getValue()+"</li>");
				else 
					Vacio=true;
				
				}
			else
				Vacio=true;
			
			StringBuffer Hijos=new StringBuffer();
			for (CompleteStructure hijo : completeST.getSons()) {
				
				String result2 = processSTDatosYMeta(hijo, completeDocuments, ambitos);
				
				if (!result2.isEmpty())
					Hijos.append(result2.toString());	
			}
			
			
			String HijosSalida = Hijos.toString();
			
			if (!HijosSalida.isEmpty()&&Vacio)
			{
			StringSalida.append("<li> "+((CompleteElementType)completeST).getName()+": </li>");
			
			}
		
		if (!HijosSalida.isEmpty())
			{
			StringSalida.append("<ul>");
			StringSalida.append(HijosSalida);
			StringSalida.append("</ul>");
			}
			
			}

		return StringSalida.toString();
		
	}
	
	
	
	
	private String processSTRecursos(CompleteStructure completeST,
			CompleteDocuments completeDocuments, ArrayList<Integer> ambitos) {
		StringBuffer StringSalida=new StringBuffer();
		boolean Vacio=true;
		if (completeST instanceof CompleteElementType)
			{
			CompleteElement E=findElem(completeST,completeDocuments.getDescription(),ambitos);
			if (E!=null)
				{
				Vacio=false;
				if (E instanceof CompleteLinkElement)
					{
					CompleteDocuments Linked=((CompleteLinkElement) E).getValue();
					
					File IconF=new File(SOURCE_FOLDER+File.separator+completeDocuments.getClavilenoid());
					IconF.mkdirs();
					
					String IconPath="";
					String Link="";
					
					boolean isAfile=false;
					
					if (StaticFuctionsHTMLOdA.isAVirtualObject(Linked) )
						IconPath=StaticFunctionsHTML.calculaIconoString(Linked.getIcon());
						
					else if (StaticFuctionsHTMLOdA.isAFile(Linked) )
					{
						for (CompleteElement Elem : Linked.getDescription()) {
							if (Elem instanceof CompleteResourceElement&&Elem.getHastype() instanceof CompleteResourceElementType&&StaticFuctionsHTMLOdA.isFileFisico(Elem.getHastype()))
								{
								if (Elem instanceof CompleteResourceElementFile)
									{
									IconPath=StaticFunctionsHTML.calculaIconoString(((CompleteResourceElementFile) Elem).getValue().getPath());
									Link=((CompleteResourceElementFile) Elem).getValue().getPath();
									}
								if (Elem instanceof CompleteResourceElementURL)
									{
									IconPath=StaticFunctionsHTML.calculaIconoString(((CompleteResourceElementURL) Elem).getValue());
									Link=((CompleteResourceElementURL) Elem).getValue();
									}
								}
						}
						isAfile=true;
						
					}
					else if (StaticFuctionsHTMLOdA.isAURL(Linked) )
					{
						for (CompleteElement Elem : Linked.getDescription()) {
							if (Elem instanceof CompleteResourceElement&&Elem.getHastype() instanceof CompleteResourceElementType&&StaticFuctionsHTMLOdA.isURI(Elem.getHastype()))
								{
								IconPath=StaticFunctionsHTML.calculaIconoStringURL();
								if (Elem instanceof CompleteResourceElementFile)
									{
									Link=((CompleteResourceElementFile) Elem).getValue().getPath();
									}
								if (Elem instanceof CompleteResourceElementURL)
									{
									Link=((CompleteResourceElementURL) Elem).getValue();
									}
								}
						}
					}
					
					String[] spliteStri=IconPath.split("/");
					String NameS = spliteStri[spliteStri.length-1];
					String Icon=SOURCE_FOLDER+File.separator+completeDocuments.getClavilenoid()+File.separator+NameS;
					
					try {
						URL url2 = new URL(IconPath);
						 URI uri2 = new URI(url2.getProtocol(), url2.getUserInfo(), url2.getHost(), url2.getPort(), url2.getPath(), url2.getQuery(), url2.getRef());
						 url2 = uri2.toURL();
						
						saveImage(url2, Icon);
					} catch (Exception e) {
						CL.getLogLines().add("Error in Icon copy, file with url ->>"+IconPath+" not found or restringed");
					}
					
					
					String NameSL="";
					if (!Link.isEmpty()&&isAfile)
					{
					String[] spliteStriL=Link.split("/");
					NameSL = spliteStriL[spliteStriL.length-1];
					String IconL=SOURCE_FOLDER+File.separator+completeDocuments.getClavilenoid()+File.separator+NameSL;
					
					try {
						URL url2L = new URL(Link);
						 URI uri2L = new URI(url2L.getProtocol(), url2L.getUserInfo(), url2L.getHost(), url2L.getPort(), url2L.getPath(), url2L.getQuery(), url2L.getRef());
						 url2L = uri2L.toURL();
						
						saveImage(url2L, IconL);
					} catch (Exception e) {
						CL.getLogLines().add("Error in Icon copy, file with url ->>"+Link+" not found or restringed");
					}
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
					
					 //TODO
					if (Link.isEmpty())
						StringSalida.append("<li> Document Linked -> <img src=\""+completeDocuments.getClavilenoid()+File.separator+NameS+"\" onmouseover=\"this.width="+width+";this.height="+height+";\" onmouseout=\"this.width="+widthmini+";this.height="+heightmini+";\" width=\""+widthmini+"\" height=\""+heightmini+"\" alt=\""+IconPath+"\" /> "+Linked.getDescriptionText()+"</li>");
					else
						if (isAfile)
							StringSalida.append("<li> Document Linked -> <img src=\""+
						completeDocuments.getClavilenoid()+File.separator+NameS+
						"\" onmouseover=\"this.width="+width+";this.height="+
						height+";\" onmouseout=\"this.width="+widthmini+";this.height="+heightmini+";\" width=\""+widthmini+
						"\" height=\""+heightmini+"\" alt=\""+IconPath+"\" /> <a href=\""+completeDocuments.getClavilenoid()+File.separator+NameSL+"\" target=\"_blank\">"+
						NameSL+"</a></li>");
						else
							StringSalida.append("<li> Document Linked -> <img src=\""+completeDocuments.getClavilenoid()
									+File.separator+NameS+"\" onmouseover=\"this.width="+width+";this.height="+height+";\" onmouseout=\"this.width="+widthmini+
									";this.height="+heightmini+";\" width=\""+widthmini+"\" height=\""+heightmini+"\" alt=\""+IconPath+
									"\" /> <a href=\""+Link+"\" target=\"_blank\">"+Link+"</a></li>");
					}
					else 
					Vacio=true;
				
				}
			else
				Vacio=true;
			
			StringBuffer Hijos=new StringBuffer();
			for (CompleteStructure hijo : completeST.getSons()) {
				
				String result2 = processSTDatosYMeta(hijo, completeDocuments, ambitos);
				
				if (!result2.isEmpty())
					Hijos.append(result2.toString());	
			}
			
			
			String HijosSalida = Hijos.toString();
			
			if (!HijosSalida.isEmpty()&&Vacio)
			{
			StringSalida.append("<li> "+((CompleteElementType)completeST).getName()+": </li>");
			
			}
		
		if (!HijosSalida.isEmpty())
			{
			StringSalida.append("<ul>");
			StringSalida.append(HijosSalida);
			StringSalida.append("</ul>");
			}
			
			}

		return StringSalida.toString();
		
	}
	
	private ArrayList<CompleteStructure> findOdAElements(
			ArrayList<CompleteStructure> sons) {
		ArrayList<CompleteStructure> Salida=new ArrayList<CompleteStructure>();
		for (CompleteStructure hastype : sons) {
			if (hastype instanceof CompleteElementType&&StaticFuctionsHTMLOdA.isDatos((CompleteElementType)hastype))
				{
				Salida.add(hastype);
				break;
				}
				
		}
		for (CompleteStructure hastype : sons) {
			if (hastype instanceof CompleteElementType&&StaticFuctionsHTMLOdA.isMetaDatos((CompleteElementType)hastype))
				{
				Salida.add(hastype);
				break;
				}
				
		}
		for (CompleteStructure hastype : sons) {
			if (hastype instanceof CompleteIterator)
				for (CompleteStructure hastype2 : hastype.getSons()) {
					if (hastype2 instanceof CompleteElementType&&StaticFuctionsHTMLOdA.isResources((CompleteElementType)hastype2))
							Salida.add(hastype2);
					break;
				}

				
		}
		return Salida;
	}

}
