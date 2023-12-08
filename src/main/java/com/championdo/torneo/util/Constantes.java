package com.championdo.torneo.util;

public class Constantes {

	// vistas
	public static final String LOGIN = "loginTorneo";
	public static final String ADULTO = "adulto";
	public static final String MENOR = "menor";
	public static final String INCLUSIVO_MINUSCULAS = "inclusivo";

	// posibles roles
	public static final String ROLE_USER = "ROLE_USER";
	public static final String ROLE_ADMIN = "ROLE_ADMIN";
	public static final String ROLE_ROOT = "ROLE_ROOT";

	// poomsaes
	public static final String INCLUSIVO = "INCLUSIVO";
	public static final String KICHO = "KICHO";
	public static final String POOMSAE1 = "1º POOMSAE";
	public static final String POOMSAE2 = "2º POOMSAE";
	public static final String POOMSAE3 = "3º POOMSAE";
	public static final String POOMSAE4 = "4º POOMSAE";
	public static final String POOMSAE5 = "5º POOMSAE";
	public static final String POOMSAE6 = "6º POOMSAE";
	public static final String POOMSAE7 = "7º POOMSAE";
	public static final String POOMSAE8 = "8º POOMSAE";
	public static final String KORYO = "KORYO";
	public static final String KUMGANG = "KUMGANG";
	public static final String TAEBEK = "TAEBEK";
	public static final String PYONGWON = "PYONGWON";
	public static final String SYPCCHIN = "SYPCCHIN";
	public static final String CHITAE = "CHITAE";
	public static final String CHUNGKWON = "CHUNGKWON";
	public static final String JANSU = "JANSU";
	public static final String ILIO = "ILIO";

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
	public static final String HOST_CORREO = "host.email";
	public static final String PORT_CORREO = "port.email";
	public static final String HOST_PAGE_NAME = "host.page.name";

	// códigos de avisos
	public static final String ERROR_BORRAR_TORNEO = "100";
	public static final String ERROR_BORRAR_TORNEO_CON_INSCRIPCIONES = "101";
    public static final String AVISO_EMAIL = "1000";
    public static final String AVISO_EMAIL_ARCHIVO_ADJUNTO = "1001";
	public static final String AVISO_MANDATO_ADULTO_YA_EXISTE = "2000";
	public static final String AVISO_MANDATO_DNI_ADULTO_YA_USADO_PARA_UN_MENOR = "2001";
	public static final String AVISO_MANDATO_DNI_ADULTO_YA_USADO_PARA_OTRO_MENOR = "2002";
	public static final String AVISO_MANDATO_DNI_ADULTO_YA_USADO_EN_INSCRIPCION_ADULTO = "2003";
	public static final String AVISO_MANDATO_MENOR_YA_EXISTE = "2004";
	public static final String AVISO_VALIDACION_ERROR_DATOS_ENTRADA = "5000";
	public static final String AVISO_VALIDACION_DATOS_NO_VALIDOS = "5001";
	public static final String AVISO_VALIDACION_NUMERO_INTENTOS_SUPERADO = "5002";
	public static final String AVISO_VALIDACION_OPERACION_FIRMADA_ANTES = "5003";
	public static final String AVISO_VALIDACION_TIEMPO_EXCEDIDO = "5004";

	// secciones donde se guardan los archivos
	public static final String SECCION_AUTORIZACION_MAYOR_18 = "autorizacionMayor18";
	public static final String SECCION_AUTORIZACION_MENOR_18 = "autorizacionMenor18";
	public static final String SECCION_MANDATO = "mandato";
	public static final String SECCION_NORMATIVA_SEPA = "normativaSEPA";
	public static final String SECCION_NORMATIVA_SEPA_FIRMADO = "normativaSEPAFirmado";
	public static final String SECCION_TORNEO = "torneo";
	public static String SECCION_WHATSAPP = "whatsapp";

	// FIRMAS - operativa original
	public static final String INSCRIPCION_TAEKWONDO = "inscripcionTaekwondo";
	public static final String INSCRIPCION_MANDATO = "inscripcionMandato";

	// extensiones de archivos
    public static final String EXTENSION_PDF = ".pdf";

	// generales
	public static final String TRUE = "true";
	public static final String FALSE = "false";
}
