package com.championdo.torneo.util;

public class Constantes {

	// vistas
	public static final String LOGIN = "loginTorneo";

	// posibles roles
	public static final String ROLES = "ROLE_ROOT,ROLE_ADMIN,ROLE_USER";

	// categorías
	public static final String INCLUSIVO = "INCLUSIVO";

	// colores cinturón
	public static final String BLANCO = "Blanco";

	// mensajes en PDFs
	public static final String ERROR_DATOS_BANCARIOS = "Error en datos bancarios";

	// claves sección administración
	public static final String FECHA_CAMPEONATO = "campeonato.fecha";
	public static final String DIRECCION_CAMPEONATO = "campeonato.direccion";
	public static final String NOMBRE_CAMPEONATO = "campeonato.nombre";
	public static final String HABILITAR_BORRAR_INSCRIPCIONES_CAMPEONATO = "inscripciones.campeonato.borrar";
	public static final String HABILITAR_BORRAR_INSCRIPCIONES_TAEKWONDO = "inscripciones.taekwondo.borrar";
	public static final String HABILITAR_CUENTA_BANCARIA = "inscripciones.cuenta.bancaria";
	public static final String CORREO_GIMNASIO = "gimnasio.correo";
	public static final String CLAVE_CORREO = "clave.correo";

	// códigos de avisos
    public static final String AVISO_EMAIL = "1000";
    public static final String AVISO_EMAIL_ARCHIVO_ADJUNTO = "1001";
    public static final String AVISO_VALIDACION_ERROR_DATOS_ENTRADA = "5000";
	public static final String AVISO_VALIDACION_DATOS_NO_VALIDOS = "5001";
	public static final String AVISO_VALIDACION_NUMERO_INTENTOS_SUPERADO = "5002";
	public static final String AVISO_VALIDACION_OPERACION_FIRMADA_ANTES = "5003";

	// secciones donde se guardan los archivos
	public static final String SECCION_AUTORIZACION_MAYOR_18 = "autorizacionMayor18";
	public static final String SECCION_AUTORIZACION_MENOR_18 = "autorizacionMenor18";
	public static final String SECCION_MANDATO = "mandato";
	public static final String SECCION_NORMATIVA_SEPA = "normativaSEPA";
	public static final String SECCION_TORNEO = "torneo";
	public static String SECCION_WHATSAPP = "whatsapp";

	// FIRMAS - operativa original
	public static final String INSCRIPCION_GIMNASIO = "inscripcionGimnasio";
}
