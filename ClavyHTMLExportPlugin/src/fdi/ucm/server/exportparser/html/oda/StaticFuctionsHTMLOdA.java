/**
 * 
 */
package fdi.ucm.server.exportparser.html.oda;

import java.util.ArrayList;
import java.util.List;

import fdi.ucm.server.modelComplete.collection.document.CompleteDocuments;
import fdi.ucm.server.modelComplete.collection.document.CompleteElement;
import fdi.ucm.server.modelComplete.collection.document.CompleteLinkElement;
import fdi.ucm.server.modelComplete.collection.document.CompleteOperationalValue;
import fdi.ucm.server.modelComplete.collection.document.CompleteResourceElement;
import fdi.ucm.server.modelComplete.collection.grammar.CompleteElementType;
import fdi.ucm.server.modelComplete.collection.grammar.CompleteGrammar;
import fdi.ucm.server.modelComplete.collection.grammar.CompleteOperationalValueType;
import fdi.ucm.server.modelComplete.collection.grammar.CompleteOperationalView;
import fdi.ucm.server.modelComplete.collection.grammar.CompleteTextElementType;

/**
 * Clase que genera las funciones estaticas para Oda.
 * @author Joaquin Gayoso-Cabada
 *
 */
public class StaticFuctionsHTMLOdA {

//	public static Integer findIdov(CompleteDocuments value) {
//		for (CompleteElement elem : value.getDescription()) {
//			if (elem instanceof CompleteTextElement&&isIDOV(elem.getHastype()))
//				return Integer.parseInt(((CompleteTextElement) elem).getValue());
//		}
//		return null;
//	}



	
	
	
	
	
	/**
	 * Obtiene su visibilidad en los casos de visibilidad asociados a las vistas
	 * @param completeElementType
	 * @return
	 */
	public static boolean getVisible(CompleteElementType completeElementType) {
		ArrayList<CompleteOperationalView> Shows = completeElementType.getShows();
		for (CompleteOperationalView show : Shows) {
			ArrayList<CompleteOperationalValueType> ShowValue = show.getValues();
			for (CompleteOperationalValueType CompleteOperationalValueType : ShowValue) {
				if (CompleteOperationalValueType.getName().equals(StaticNamesHTMLOdA.VISIBLESHOWN))
					if (CompleteOperationalValueType.getDefault().equals(Boolean.toString(true)))
						return true;
					else if (CompleteOperationalValueType.getDefault().equals(Boolean.toString(false)))
							return false;

			}
		}
		return false;
	}



	/**
	 * Obtiene su navegabilidad en los casos de visibilidad asociados a las vistas
	 * @param completeElementType
	 * @return
	 */
	public static boolean getBrowseable(CompleteElementType completeElementType) {
		ArrayList<CompleteOperationalView> Shows = completeElementType.getShows();
		for (CompleteOperationalView show : Shows) {
			ArrayList<CompleteOperationalValueType> ShowValue = show.getValues();
			for (CompleteOperationalValueType CompleteOperationalValueType : ShowValue) {
				if (CompleteOperationalValueType.getName().equals(StaticNamesHTMLOdA.BROWSERSHOWN))
					if (CompleteOperationalValueType.getDefault().equals(Boolean.toString(true)))
						return true;
					else if (CompleteOperationalValueType.getDefault().equals(Boolean.toString(false)))
							return false;

			}
		}
		return false;
	}

	/**
	 * Funcion que tetorna el icono de un recurso Objeto digital
	 * @param objetoDigital
	 * @return
	 */
	public static String getIcon(CompleteDocuments objetoDigital) {
		
		return objetoDigital.getIcon();

	}

	
	
	/**
	 * Revisa si un elemento es VirtualObject
	 * @param hastype
	 * @return
	 */
	public static boolean isVirtualObject(CompleteGrammar hastype) {
		
		ArrayList<CompleteOperationalView> Shows = hastype.getViews();
		for (CompleteOperationalView show : Shows) {
			
			if (show.getName().equals(StaticNamesHTMLOdA.META))
			{
				ArrayList<CompleteOperationalValueType> ShowValue = show.getValues();
				for (CompleteOperationalValueType CompleteOperationalValueType : ShowValue) {
					if (CompleteOperationalValueType.getName().equals(StaticNamesHTMLOdA.TYPE))
						if (CompleteOperationalValueType.getDefault().equals(StaticNamesHTMLOdA.VIRTUAL_OBJECT)) 
										return true;

				}
			}
		}
		return false;
	}
	
	/**
	 * Revisa si un elemento es VirtualObject
	 * @param ElementTypeId
	 * @return
	 */
	public static boolean isAVirtualObject(CompleteDocuments element) {
		
		if (isVirtualObject(element.getDocument()))
			return true;
		
		return false;
	}
	
	/**
	 * Revisa si un elemento es VirtualObject
	 * @param ElementTypeId
	 * @return
	 */
	public static boolean isAFile(CompleteDocuments element) {
		

			if (isFile(element.getDocument()))
				return true;
		return false;
	}
	
//	/**
//	 * Revisa si un elemento es Descripcion
//	 * @param hastype
//	 * @return
//	 */
//	public static boolean isDescription(ElementType hastype) {
//		
//		ArrayList<OperationalView> Shows = hastype.getShows();
//		for (OperationalView show : Shows) {
//			
//			if (show.getName().equals(StaticNamesOda2.META))
//			{
//				ArrayList<OperationalValueType> ShowValue = show.getValues();
//				for (OperationalValueType OperationalValueType : ShowValue) {
//					if (OperationalValueType.getName().equals(StaticNamesOda2.TYPE))
//						if (OperationalValueType.getDefault().equals(StaticNamesOda2.DESCRIPTION)) 
//								return true;
//
//				}
//			}
//		}
//		return false;
//	}
	
//	/**
//	 * Revisa si un elemento es IDOV
//	 * @param hastype
//	 * @return
//	 */
//	public static boolean isIDOV(CompleteElementType hastype) {
//		
//		ArrayList<CompleteOperationalView> Shows = hastype.getShows();
//		for (CompleteOperationalView show : Shows) {
//			
//			if (show.getName().equals(StaticNamesOda2.META))
//			{
//				ArrayList<CompleteOperationalValueType> ShowValue = show.getValues();
//				for (CompleteOperationalValueType CompleteOperationalValueType : ShowValue) {
//					if (CompleteOperationalValueType.getName().equals(StaticNamesOda2.TYPE))
//						if (CompleteOperationalValueType.getDefault().equals(StaticNamesOda2.IDOV)) 
//										return true;
//				}
//			}
//		}
//		return false;
//	}
	
	/**
	 * Revisa si un elemento es File
	 * @param hastype
	 * @return
	 */
	public static boolean isFile(CompleteGrammar hastype) {
		
		ArrayList<CompleteOperationalView> Shows = hastype.getViews();
		for (CompleteOperationalView show : Shows) {
			
			if (show.getName().equals(StaticNamesHTMLOdA.META))
			{
				ArrayList<CompleteOperationalValueType> ShowValue = show.getValues();
				for (CompleteOperationalValueType CompleteOperationalValueType : ShowValue) {
					if (CompleteOperationalValueType.getName().equals(StaticNamesHTMLOdA.TYPE))
						if (CompleteOperationalValueType.getDefault().equals(StaticNamesHTMLOdA.FILE)) 
										return true;
				}
			}
		}
		return false;
	}
	
	
	/**
	 * Revisa si un elemento es FileResource
	 * @param hastype
	 * @return
	 */
	public static boolean isFileFisico(CompleteElementType hastype) {
		
		ArrayList<CompleteOperationalView> Shows = hastype.getShows();
		for (CompleteOperationalView show : Shows) {
			
			if (show.getName().equals(StaticNamesHTMLOdA.META))
			{
				ArrayList<CompleteOperationalValueType> ShowValue = show.getValues();
				for (CompleteOperationalValueType CompleteOperationalValueType : ShowValue) {
					if (CompleteOperationalValueType.getName().equals(StaticNamesHTMLOdA.TYPE))
						if (CompleteOperationalValueType.getDefault().equals(StaticNamesHTMLOdA.FILERESOURCE)) 
										return true;

				}
			}
		}
		return false;
	}
	
	/**
	 * Revisa si un elemento es Owner
	 * @param hastype
	 * @return
	 */
	public static boolean isOwner(CompleteElementType hastype) {
		
		ArrayList<CompleteOperationalView> Shows = hastype.getShows();
		for (CompleteOperationalView show : Shows) {
			
			if (show.getName().equals(StaticNamesHTMLOdA.META))
			{
				ArrayList<CompleteOperationalValueType> ShowValue = show.getValues();
				for (CompleteOperationalValueType CompleteOperationalValueType : ShowValue) {
					if (CompleteOperationalValueType.getName().equals(StaticNamesHTMLOdA.TYPE))
						if (CompleteOperationalValueType.getDefault().equals(StaticNamesHTMLOdA.IDOV_OWNER)) 
										return true;
				}
			}
		}
		return false;
	}
	
	/**
	 * Revisa si un elemento es METADATOS
	 * @param hastype
	 * @return
	 */
	public static boolean isMetaDatos(CompleteElementType hastype) {
		
		ArrayList<CompleteOperationalView> Shows = hastype.getShows();
		for (CompleteOperationalView show : Shows) {
			
			if (show.getName().equals(StaticNamesHTMLOdA.META))
			{
				ArrayList<CompleteOperationalValueType> ShowValue = show.getValues();
				for (CompleteOperationalValueType CompleteOperationalValueType : ShowValue) {
					if (CompleteOperationalValueType.getName().equals(StaticNamesHTMLOdA.TYPE))
						if (CompleteOperationalValueType.getDefault().equals(StaticNamesHTMLOdA.METADATOS)) 
										return true;

				}
			}
		}
		return false;
	}
	
	/**
	 * Revisa si un elemento es METADATOS
	 * @param hastype
	 * @return
	 */
	public static boolean isDatos(CompleteElementType hastype) {
		
		ArrayList<CompleteOperationalView> Shows = hastype.getShows();
		for (CompleteOperationalView show : Shows) {
			
			if (show.getName().equals(StaticNamesHTMLOdA.META))
			{
				ArrayList<CompleteOperationalValueType> ShowValue = show.getValues();
				for (CompleteOperationalValueType CompleteOperationalValueType : ShowValue) {
					if (CompleteOperationalValueType.getName().equals(StaticNamesHTMLOdA.TYPE))
						if (CompleteOperationalValueType.getDefault().equals(StaticNamesHTMLOdA.DATOS)) 
										return true;

				}
			}
		}
		return false;
	}
	
	/**
	 * Revisa si un elemento es Resources
	 * @param hastype
	 * @return
	 */
	public static boolean isResources(CompleteElementType hastype) {
		
		ArrayList<CompleteOperationalView> Shows = hastype.getShows();
		for (CompleteOperationalView show : Shows) {
			
			if (show.getName().equals(StaticNamesHTMLOdA.META))
			{
				ArrayList<CompleteOperationalValueType> ShowValue = show.getValues();
				for (CompleteOperationalValueType CompleteOperationalValueType : ShowValue) {
					if (CompleteOperationalValueType.getName().equals(StaticNamesHTMLOdA.TYPE))
						if (CompleteOperationalValueType.getDefault().equals(StaticNamesHTMLOdA.RESOURCE)) 
										return true;
				}
			}
		}
		return false;
	}


	
	/**
	 * Revisa si un elemento es Extensible
	 * @param hastype
	 * @return
	 */
	public static boolean isExtensible(CompleteElementType hastype) {
		
		ArrayList<CompleteOperationalView> Shows = hastype.getShows();
		for (CompleteOperationalView show : Shows) {
			
			if (show.getName().equals(StaticNamesHTMLOdA.ODA))
			{
				ArrayList<CompleteOperationalValueType> ShowValue = show.getValues();
				for (CompleteOperationalValueType CompleteOperationalValueType : ShowValue) {
					if (CompleteOperationalValueType.getName().equals(StaticNamesHTMLOdA.EXTENSIBLE))
						if (CompleteOperationalValueType.getDefault().equals(Boolean.toString(true)))
							return true;
						else if (CompleteOperationalValueType.getDefault().equals(Boolean.toString(false)))
								return false;
				}
			}
		}
		return true;
	}
	
	/**
	 * Funcion que retorna si el OV es publico
	 * @param objetoDigital
	 * @return
	 */
	public static boolean getPublic(CompleteDocuments objetoDigital) {
		

			if (isVirtualObject(objetoDigital.getDocument()))
				{
				ArrayList<CompleteOperationalValue> ShowsInst = objetoDigital.getViewsValues();
				for (CompleteOperationalValue show : ShowsInst) {
					if (show.getType().getView().getName().equals(StaticNamesHTMLOdA.ODA)&&show.getType().getName().equals(StaticNamesHTMLOdA.PUBLIC))
						if (show.getValue().equals(Boolean.toString(true)))
							return true;
						else if (show.getValue().equals(Boolean.toString(false)))
							return false;
				}
				
				ArrayList<CompleteOperationalView> Shows = objetoDigital.getDocument().getViews();
				for (CompleteOperationalView show : Shows) {
					if (show.getName().equals(StaticNamesHTMLOdA.ODA))
					{
					ArrayList<CompleteOperationalValueType> ShowValue = show.getValues();
					for (CompleteOperationalValueType CompleteOperationalValueType : ShowValue) {
						if (CompleteOperationalValueType.getName().equals(StaticNamesHTMLOdA.PUBLIC))
							if (CompleteOperationalValueType.getDefault().equals(Boolean.toString(true)))
									return true;
							else if (CompleteOperationalValueType.getDefault().equals(Boolean.toString(false)))
									return false;

					}
					}
				}
				return false;
				}

		return false;

	}
	

	
	
	/**
	 * Funcion que retorna si el OV es publico
	 * @param objetoDigital
	 * @return
	 */
	public static boolean getPrivate(CompleteDocuments objetoDigital) {
		

		if (isVirtualObject(objetoDigital.getDocument()))
				{
			ArrayList<CompleteOperationalValue> ShowsInst = objetoDigital.getViewsValues();
				for (CompleteOperationalValue show : ShowsInst) {
					if (show.getType().getView().getName().equals(StaticNamesHTMLOdA.ODA)&&show.getType().getName().equals(StaticNamesHTMLOdA.PRIVATE))
						if (show.getValue().equals(Boolean.toString(true)))
							return true;
						else if (show.getValue().equals(Boolean.toString(false)))
							return false;
				}
				
				ArrayList<CompleteOperationalView> Shows = objetoDigital.getDocument().getViews();
				for (CompleteOperationalView show : Shows) {
					if (show.getName().equals(StaticNamesHTMLOdA.ODA))
					{
					ArrayList<CompleteOperationalValueType> ShowValue = show.getValues();
					for (CompleteOperationalValueType CompleteOperationalValueType : ShowValue) {
						if (CompleteOperationalValueType.getName().equals(StaticNamesHTMLOdA.PRIVATE))
							if (CompleteOperationalValueType.getDefault().equals(Boolean.toString(true)))
								return true;
							else if (CompleteOperationalValueType.getDefault().equals(Boolean.toString(false)))
									return false;

					}
					}
				}
				return true;
				}
		
		return true;

	}
	

	
	
	

	/**
	 * Funcion que retorna el icono de un MetaValue donde esta definido el icono
	 * @param elem
	 * @return
	 */
	public static boolean getVisible(CompleteElement elem) {
		
		ArrayList<CompleteOperationalValue> ShowsInst = elem.getShows();
		for (CompleteOperationalValue show : ShowsInst) {
			if (show.getType().getName().equals(StaticNamesHTMLOdA.VISIBLESHOWN))
				if (show.getValue().equals(Boolean.toString(true)))
					return true;
				else if (show.getValue().equals(Boolean.toString(false)))
					return false;
		}
		
		ArrayList<CompleteOperationalView> Shows = elem.getHastype().getShows();
		for (CompleteOperationalView show : Shows) {
			ArrayList<CompleteOperationalValueType> ShowValue = show.getValues();
			for (CompleteOperationalValueType CompleteOperationalValueType : ShowValue) {
				if (CompleteOperationalValueType.getName().equals(StaticNamesHTMLOdA.VISIBLESHOWN))
					if (CompleteOperationalValueType.getDefault().equals(Boolean.toString(true)))
							return true;
					else if (CompleteOperationalValueType.getDefault().equals(Boolean.toString(false)))
							return false;
			}
		}
		return false;
		
	}

	/**
	 * Encuentra relation Value File en File
	 * @param description
	 * @return
	 */
	public static CompleteResourceElement findMetaValueFile(
			List<CompleteElement> description) {
		for (CompleteElement completeElement : description) {
			if (completeElement instanceof CompleteResourceElement&&StaticFuctionsHTMLOdA.isFileFisico(completeElement.getHastype()))
				return (CompleteResourceElement) completeElement;
		}
		return null;
	}

	/**
	 * Encuentra relacion entre el file y el OV dueño.
	 * @param description
	 * @return
	 */
	public static CompleteLinkElement findMetaValueIDOVowner(
			List<CompleteElement> description) {
		for (CompleteElement completeElement : description) {
			if (completeElement instanceof CompleteLinkElement&&StaticFuctionsHTMLOdA.isOwner(completeElement.getHastype()))
				return (CompleteLinkElement) completeElement;
		}
		return null;
	}







	/**
	 * Clase que define si es numerico
	 * @param hastype
	 * @return
	 */
	public static boolean isNumeric(CompleteElementType hastype) {
		ArrayList<CompleteOperationalView> Shows = hastype.getShows();
		for (CompleteOperationalView show : Shows) {	
			if (show.getName().equals(StaticNamesHTMLOdA.METATYPE))
			{
				ArrayList<CompleteOperationalValueType> ShowValue = show.getValues();
				for (CompleteOperationalValueType showValues : ShowValue) {
					if (showValues.getName().equals(StaticNamesHTMLOdA.METATYPETYPE))
							if (showValues.getDefault().equals(StaticNamesHTMLOdA.NUMERIC)) 
										return true;
				}
			}
		}
		return false;
	}








	public static boolean isNumeric(CompleteElement elem) {
		ArrayList<CompleteOperationalValue> ShowsInst = elem.getShows();
		for (CompleteOperationalValue show : ShowsInst) {
			if (show.getType().getView().getName().equals(StaticNamesHTMLOdA.METATYPE)&&show.getType().getName().equals(StaticNamesHTMLOdA.METATYPETYPE))
				if (show.getValue().equals(StaticNamesHTMLOdA.NUMERIC))
					return true;
		}
		
		return isNumeric(elem.getHastype());
		
	}








	public static boolean isDate(CompleteElementType attribute) {
		ArrayList<CompleteOperationalView> Shows = attribute.getShows();
		for (CompleteOperationalView show : Shows) {	
			if (show.getName().equals(StaticNamesHTMLOdA.METATYPE))
			{
				ArrayList<CompleteOperationalValueType> ShowValue = show.getValues();
				for (CompleteOperationalValueType showValues : ShowValue) {
					if (showValues.getName().equals(StaticNamesHTMLOdA.METATYPETYPE))
							if (showValues.getDefault().equals(StaticNamesHTMLOdA.DATE)) 
										return true;
				}
			}
		}
		return false;
	}








	public static Integer getVocNumber(CompleteTextElementType attribute) {
		ArrayList<CompleteOperationalView> Shows = attribute.getShows();
		for (CompleteOperationalView show : Shows) {	
			if (show.getName().equals(StaticNamesHTMLOdA.VOCABULARY))
			{
				ArrayList<CompleteOperationalValueType> ShowValue = show.getValues();
				for (CompleteOperationalValueType showValues : ShowValue) {
					if (showValues.getName().equals(StaticNamesHTMLOdA.VOCNUMBER))
						try {
							return Integer.parseInt(showValues.getDefault());
						} catch (Exception e) {
							return null;
						}	

							
				}
			}
		}
		return null;
	}
	
	
	public static Boolean getVocCompartido(CompleteTextElementType attribute) {
		ArrayList<CompleteOperationalView> Shows = attribute.getShows();
		for (CompleteOperationalView show : Shows) {	
			if (show.getName().equals(StaticNamesHTMLOdA.VOCABULARY))
			{
				ArrayList<CompleteOperationalValueType> ShowValue = show.getValues();
				for (CompleteOperationalValueType showValues : ShowValue) {
					if (showValues.getName().equals(StaticNamesHTMLOdA.COMPARTIDO))
						try {
							return Boolean.parseBoolean(showValues.getDefault());
						} catch (Exception e) {
							return true;
						}	

							
				}
			}
		}
		return true;
	}








	public static boolean isControled(CompleteElementType hastype) {
		ArrayList<CompleteOperationalView> Shows = hastype.getShows();
		for (CompleteOperationalView show : Shows) {	
			if (show.getName().equals(StaticNamesHTMLOdA.METATYPE))
			{
				ArrayList<CompleteOperationalValueType> ShowValue = show.getValues();
				for (CompleteOperationalValueType showValues : ShowValue) {
					if (showValues.getName().equals(StaticNamesHTMLOdA.METATYPETYPE))
							if (showValues.getDefault().equals(StaticNamesHTMLOdA.CONTROLED)) 
										return true;
				}
			}
		}
		return false;
	}



	public static boolean isAURL(CompleteDocuments recursoAProcesarC) {
		if (isURL(recursoAProcesarC.getDocument()))
			return true;
	return false;
	}



	public static boolean isURL(CompleteGrammar document) {
		ArrayList<CompleteOperationalView> Shows = document.getViews();
		for (CompleteOperationalView show : Shows) {
			
			if (show.getName().equals(StaticNamesHTMLOdA.META))
			{
				ArrayList<CompleteOperationalValueType> ShowValue = show.getValues();
				for (CompleteOperationalValueType CompleteOperationalValueType : ShowValue) {
					if (CompleteOperationalValueType.getName().equals(StaticNamesHTMLOdA.TYPE))
						if (CompleteOperationalValueType.getDefault().equals(StaticNamesHTMLOdA.URL)) 
										return true;
				}
			}
		}
		return false;
	}



	public static CompleteResourceElement findMetaValueUri(
			List<CompleteElement> description) {
		for (CompleteElement completeElement : description) {
			if (completeElement instanceof CompleteResourceElement&&StaticFuctionsHTMLOdA.isURI(completeElement.getHastype()))
				return (CompleteResourceElement) completeElement;
		}
		return null;
	}


	/**
	 * Revisa si un elemento es FileResource
	 * @param hastype
	 * @return
	 */
	public static boolean isURI(CompleteElementType hastype) {
		
		ArrayList<CompleteOperationalView> Shows = hastype.getShows();
		for (CompleteOperationalView show : Shows) {
			
			if (show.getName().equals(StaticNamesHTMLOdA.META))
			{
				ArrayList<CompleteOperationalValueType> ShowValue = show.getValues();
				for (CompleteOperationalValueType CompleteOperationalValueType : ShowValue) {
					if (CompleteOperationalValueType.getName().equals(StaticNamesHTMLOdA.TYPE))
						if (CompleteOperationalValueType.getDefault().equals(StaticNamesHTMLOdA.URI)) 
										return true;

				}
			}
		}
		return false;
	}



	public static Integer getIDODAD(CompleteElementType attribute) {
		ArrayList<CompleteOperationalView> Shows = attribute.getShows();
		for (CompleteOperationalView show : Shows) {
			
			if (show.getName().equals(StaticNamesHTMLOdA.PRESNTACION))
			{
				ArrayList<CompleteOperationalValueType> ShowValue = show.getValues();
				for (CompleteOperationalValueType CompleteOperationalValueType : ShowValue) {
					if (CompleteOperationalValueType.getName().equals(StaticNamesHTMLOdA.OdaID))
						try {
							Integer I=Integer.parseInt(CompleteOperationalValueType.getDefault());
								return I;
						} catch (Exception e) {
							return null;
						}
						

				}
			}
		}
		return null;
		
	}



	public static boolean isIDOV(CompleteTextElementType hastype) {
		ArrayList<CompleteOperationalView> Shows = hastype.getShows();
		for (CompleteOperationalView show : Shows) {
			
			if (show.getName().equals(StaticNamesHTMLOdA.META))
			{
				ArrayList<CompleteOperationalValueType> ShowValue = show.getValues();
				for (CompleteOperationalValueType CompleteOperationalValueType : ShowValue) {
					if (CompleteOperationalValueType.getName().equals(StaticNamesHTMLOdA.TYPE))
						if (CompleteOperationalValueType.getDefault().equals(StaticNamesHTMLOdA.IDOV)) 
										return true;

				}
			}
		}
		return false;
	}



	public static void findPresentacionYCompleta(
			CompleteElementType attribute,Integer IdovNuevo) {
		ArrayList<CompleteOperationalView> Shows = attribute.getShows();
		for (CompleteOperationalView show : Shows) {
			
			if (show.getName().equals(StaticNamesHTMLOdA.PRESNTACION))
			{
				boolean found=false;
				ArrayList<CompleteOperationalValueType> ShowValue = show.getValues();
				for (CompleteOperationalValueType CompleteOperationalValueType : ShowValue) {
					if (CompleteOperationalValueType.getName().equals(StaticNamesHTMLOdA.OdaID))
						try {
							Integer.parseInt(CompleteOperationalValueType.getDefault());
								found=true;
						} catch (Exception e) {
							
						}
						

				}
				
				if (!found)
					show.getValues().add(new CompleteOperationalValueType(StaticNamesHTMLOdA.OdaID, Integer.toString(IdovNuevo), show));
			}
		}
	}



	public static boolean isIgnored(CompleteElementType hastype) {
		ArrayList<CompleteOperationalView> Shows = hastype.getShows();
		for (CompleteOperationalView show : Shows) {
			
			if (show.getName().equals(StaticNamesHTMLOdA.META))
			{
				ArrayList<CompleteOperationalValueType> ShowValue = show.getValues();
				for (CompleteOperationalValueType CompleteOperationalValueType : ShowValue) {
					if (CompleteOperationalValueType.getName().equals(StaticNamesHTMLOdA.TYPE))
						if (CompleteOperationalValueType.getDefault().equals(StaticNamesHTMLOdA.IGNORED)) 
										return true;

				}
			}
		}
		return false;
	}



	public static String getDescription(CompleteGrammar completeGrammar) {
		ArrayList<CompleteOperationalView> Shows = completeGrammar.getViews();
		for (CompleteOperationalView show : Shows) {
			
			if (show.getName().equals(StaticNamesHTMLOdA.META))
			{
				ArrayList<CompleteOperationalValueType> ShowValue = show.getValues();
				for (CompleteOperationalValueType CompleteOperationalValueType : ShowValue) {
					if (CompleteOperationalValueType.getName().equals(StaticNamesHTMLOdA.DESCRIPTIONNAME))
								return CompleteOperationalValueType.getDefault();

				}
			}
		}
		return "";
	}
	
}
