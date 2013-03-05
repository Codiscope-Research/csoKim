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

Calendar._DN_cat = new Array("Diumenge","Dilluns","Dimarts","Dimecres","Dijous","Divendres","Dissabte","Diumenge");

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
Calendar._SDN_cat = new Array(	"Diu", "Dll", "Dma", "Dme", "Djo", "Dve", "Dss", "Diu");

// First day of the week. "0" means display Sunday first, "1" means display
// Monday first, etc.
Calendar._FD_cat = 1;

// full month names
Calendar._MN_cat = new Array
("Gener",
 "Febrer",
 "Març",
 "Abril",
 "Maig",
 "Juny",
 "Juliol",
 "Agost",
 "Setembre",
 "Octubre",
 "Novembre",
 "Desembre");

// short month names
Calendar._SMN_cat = new Array
("Gen",
 "Feb",
 "Mar",
 "Abr",
 "Mai",
 "Jun",
 "Jul",
 "Ago",
 "Set",
 "Oct",
 "Nov",
 "Des");

// tooltips
Calendar._TT_cat = {};
Calendar._TT_cat["INFO"] = "";

Calendar._TT_cat["ABOUT"] ="";
Calendar._TT_cat["ABOUT_TIME"] = "";

Calendar._TT_cat["PREV_YEAR"] = "Any anterior (premer i mantenir per menú)";
Calendar._TT_cat["PREV_MONTH"] = "Mes anterior (premer i mantenir per menú)";
Calendar._TT_cat["GO_TODAY"] = "Avui";
Calendar._TT_cat["NEXT_MONTH"] = "Mes següent (premer i mantenir per menú)";
Calendar._TT_cat["NEXT_YEAR"] = "Any següent (premer i mantenir per menú)";
Calendar._TT_cat["SEL_DATE"] = "Seleccionar data";
Calendar._TT_cat["DRAG_TO_MOVE"] = "Arrosegar per moure";
Calendar._TT_cat["PART_TODAY"] = " (Avui)";

// the following is to inform that "%s" is to be the first day of week
// %s will be replaced with the day name.
Calendar._TT_cat["DAY_FIRST"] = "Fer %s primer dia de la semana";

// This may be locale-dependent.  It specifies the week-end days, as an array
// of comma-separated numbers.  The numbers are from 0 to 6: 0 means Sunday, 1
// means Monday, etc.
Calendar._TT_cat["WEEKEND"] = "0,6";

Calendar._TT_cat["CLOSE"] = "Tancar";
Calendar._TT_cat["TODAY"] = "Avui";
Calendar._TT_cat["TIME_PART"] = "(Majúscula-)Clic o arrosegar per canviar valor";

// date formats
Calendar._TT_cat["DEF_DATE_FORMAT"] = "%d/%m/%Y";
Calendar._TT_cat["TT_DATE_FORMAT"] = "%A, %e de %B de %Y";

Calendar._TT_cat["WK"] = "sem";
Calendar._TT_cat["TIME"] = "Hora:";
