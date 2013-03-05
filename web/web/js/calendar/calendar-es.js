// ** I18N

// Calendar ES (spanish) language
// Author: Mihai Bazon, <mihai_bazon@yahoo.com>
// Updater: Servilio Afre Puentes <servilios@yahoo.com>
// Updated: 2004-06-03
// Encoding: utf-8
// Distributed under the same terms as the calendar itself.

// For translators: please use UTF-8 if possible.  We strongly believe that
// Unicode is the answer to a real internationalized world.  Also please
// include your contact information in the header, as can be seen above.

// full day names
Calendar._DN_cas = new Array
("Domingo","Lunes", "Martes", "Miércoles", "Jueves", "Viernes","Sábado", "Domingo");

// Please note that the following array of short day names (and the same goes
// for short month names, _SMN) isn't absolutely necessary.  We give it here
// for exemplification on how one can customize the short day names, but if
// they are simply the first N letters of the full name you can simply say:
//
//   Calendar._SDN_len = N; // short day name length
//   Calendar._SMN_len = N; // short month name length
//
// If N = 3 then this is not needed either since we assume a value of 3 if not
// present, to be compatible with translation files that were written before
// this feature.

// short day names
Calendar._SDN_cas = new Array
("Dom",
 "Lun",
 "Mar",
 "Mié",
 "Jue",
 "Vie",
 "Sáb",
 "Dom");

// First day of the week. "0" means display Sunday first, "1" means display
// Monday first, etc.
Calendar._FD_cas = 1;

// full month names
Calendar._MN_cas = new Array
("Enero",
 "Febrero",
 "Marzo",
 "Abril",
 "Mayo",
 "Junio",
 "Julio",
 "Agosto",
 "Septiembre",
 "Octubre",
 "Noviembre",
 "Diciembre");

// short month names
Calendar._SMN_cas = new Array
("Ene",
 "Feb",
 "Mar",
 "Abr",
 "May",
 "Jun",
 "Jul",
 "Ago",
 "Sep",
 "Oct",
 "Nov",
 "Dic");

// tooltips
Calendar._TT_cas = {};
Calendar._TT_cas["INFO"] = "Acerca del calendario";

Calendar._TT_cas["ABOUT"] =
"";

Calendar._TT_cas["PREV_YEAR"] = "Año anterior \n" + "(presionar y mantener para menú)";
Calendar._TT_cas["PREV_MONTH"] = "Mes anterior \n" + "(presionar y mantener para menú)";
Calendar._TT_cas["GO_TODAY"] = "Ir a hoy";
Calendar._TT_cas["NEXT_MONTH"] = "Mes siguiente \n" + "(presionar y mantener para menú)";
Calendar._TT_cas["NEXT_YEAR"] = "Año siguiente \n" + "(presionar y mantener para menú)";
Calendar._TT_cas["SEL_DATE"] = "Seleccionar fecha";
Calendar._TT_cas["DRAG_TO_MOVE"] = "Arrastrar para mover";
Calendar._TT_cas["PART_TODAY"] = " (hoy)";

// the following is to inform that "%s" is to be the first day of week
// %s will be replaced with the day name.
Calendar._TT_cas["DAY_FIRST"] = "Hacer %s primer día de la semana";

// This may be locale-dependent.  It specifies the week-end days, as an array
// of comma-separated numbers.  The numbers are from 0 to 6: 0 means Sunday, 1
// means Monday, etc.
Calendar._TT_cas["WEEKEND"] = "0,6";

Calendar._TT_cas["CLOSE"] = "Cerrar";
Calendar._TT_cas["TODAY"] = "Hoy";
Calendar._TT_cas["TIME_PART"] = "(Mayúscula-)Clic o arrastre para cambiar valor";

// date formats
Calendar._TT_cas["DEF_DATE_FORMAT"] = "%d/%m/%Y";
Calendar._TT_cas["TT_DATE_FORMAT"] = "%A, %e de %B de %Y";

Calendar._TT_cas["WK"] = "sem";
Calendar._TT_cas["TIME"] = "Hora:";
