package fdi.ucm.server.exportparser.html;



public class StaticFunctionsHTML {

	private static final String CLAVYICONOS = "http://a-note.fdi.ucm.es/Clavy/";

	public static String calculaIconoString(String textopath) {
		if
		(
				//Imagen
		textopath.toLowerCase().endsWith(".jpg")
		||
		textopath.toLowerCase().endsWith(".jpge")	
		||
		textopath.toLowerCase().endsWith(".gif")
		||
		textopath.toLowerCase().endsWith(".png")
		)
		return textopath;
	else
		if (textopath.toLowerCase().endsWith(".rar"))
			return CLAVYICONOS+StaticIconos.ICONORAR;
	else
		if (textopath.toLowerCase().endsWith(".avi"))
			return CLAVYICONOS+StaticIconos.ICONOAVI;
		else
			if (textopath.toLowerCase().endsWith(".doc"))
				return CLAVYICONOS+StaticIconos.ICONODOC;
			else
				if (textopath.toLowerCase().endsWith(".docx"))
					return CLAVYICONOS+StaticIconos.ICONODOCX;
				else
					if (textopath.toLowerCase().endsWith(".pdf"))
						return CLAVYICONOS+StaticIconos.ICONOPDF;
					else
						if (textopath.toLowerCase().endsWith(".html"))
							return CLAVYICONOS+StaticIconos.ICONOHTML;
						else
							if (textopath.toLowerCase().endsWith(".htm"))
								return CLAVYICONOS+StaticIconos.ICONOHTML;
							else
								if (textopath.toLowerCase().endsWith(".php"))
									return CLAVYICONOS+StaticIconos.ICONOHTML;
								else
									if (textopath.toLowerCase().endsWith(".ppt"))
										return CLAVYICONOS+StaticIconos.ICONOPPT;
									else
										if (textopath.toLowerCase().endsWith(".pptx"))
											return CLAVYICONOS+StaticIconos.ICONOPPTX;
										else
											if (textopath.toLowerCase().endsWith(".mov"))
												return CLAVYICONOS+StaticIconos.ICONOMOV;
											else
												if (textopath.toLowerCase().endsWith(".fla"))
													return CLAVYICONOS+StaticIconos.ICONOFLA;
												else
													if (textopath.toLowerCase().endsWith(".swf"))
														return CLAVYICONOS+StaticIconos.ICONOSWF;
													else
														if (textopath.toLowerCase().endsWith(".midi"))
															return CLAVYICONOS+StaticIconos.ICONOMIDI;
														else
															if (textopath.toLowerCase().endsWith(".mp3"))
																return CLAVYICONOS+StaticIconos.ICONOMP3;
															else
																if (textopath.toLowerCase().endsWith(".mp4"))
																	return CLAVYICONOS+StaticIconos.ICONOMP4;
																else
																	if (textopath.toLowerCase().endsWith(".mpg"))
																		return CLAVYICONOS+StaticIconos.ICONOMPG;
																	else
																		if (textopath.toLowerCase().endsWith(".odt"))
																			return CLAVYICONOS+StaticIconos.ICONOODT;
																		else
																			if (textopath.toLowerCase().endsWith(".ods"))
																				return CLAVYICONOS+StaticIconos.ICONOODS;
																			else
																				if (textopath.toLowerCase().endsWith(".zip"))
																					return CLAVYICONOS+StaticIconos.ICONOZIP;
																				else
																					if (textopath.toLowerCase().endsWith(".rtf"))
																						return CLAVYICONOS+StaticIconos.ICONORTF;
																					else
																						if (textopath.toLowerCase().endsWith(".ttf"))
																							return CLAVYICONOS+StaticIconos.ICONOTTF;
																						else
																							if (textopath.toLowerCase().endsWith(".txt"))
																								return CLAVYICONOS+StaticIconos.ICONOTXT;
																							else
																								if (textopath.toLowerCase().endsWith(".wav"))
																									return CLAVYICONOS+StaticIconos.ICONOWAV;
																								else
																									if (textopath.toLowerCase().endsWith(".wma"))
																										return CLAVYICONOS+StaticIconos.ICONOWMA;
																									else
																										if (textopath.toLowerCase().endsWith(".wmv"))
																											return CLAVYICONOS+StaticIconos.ICONOWMV;
																										else
																											if (textopath.toLowerCase().endsWith(".xls"))
																												return CLAVYICONOS+StaticIconos.ICONOXLS;
																											else
																												if (textopath.toLowerCase().endsWith(".xlsx"))
																													return CLAVYICONOS+StaticIconos.ICONOXLSX;
																												else
																													if (textopath.toLowerCase().endsWith(".xml"))
																														return CLAVYICONOS+StaticIconos.ICONOXML;
	
		return CLAVYICONOS+StaticIconos.ICONODEFAULT;
											
	}

}