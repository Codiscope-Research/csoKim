package es.intos.gdscso.utils;

import java.util.Comparator;

import es.intos.gdscso.on.ComparativaSrvTable;

@SuppressWarnings("rawtypes")
public class OrderComparativaServByName implements Comparator{

	public static String[]	order			= new String[] { "nomServei ASC", "nomServei DESC", "estat ASC", "estat DESC" };
	public String			wichComparator	= "";

	public OrderComparativaServByName( String wichComparator ) {

		super();
		this.wichComparator = wichComparator;
	}

	public int compare( Object o1, Object o2 ){

		ComparativaSrvTable comparativaSrvTable1 = (ComparativaSrvTable) o1;
		ComparativaSrvTable comparativaSrvTable2 = (ComparativaSrvTable) o2;

		int resultComparation = 0;

		if (this.wichComparator.equals(order[0])) {
			resultComparation = comparativaSrvTable1.getServeiActual().compareToIgnoreCase(comparativaSrvTable2.getServeiActual());
		}
		if (this.wichComparator.equals(order[1])) {
			resultComparation = comparativaSrvTable2.getServeiActual().compareToIgnoreCase(comparativaSrvTable1.getServeiActual());
		}
		if (this.wichComparator.equals(order[2])) {
			resultComparation = comparativaSrvTable2.getEstat().compareToIgnoreCase(comparativaSrvTable1.getEstat());
		}
		if (this.wichComparator.equals(order[3])) {
			resultComparation = comparativaSrvTable1.getEstat().compareToIgnoreCase(comparativaSrvTable2.getEstat());
		}
		return resultComparation;
	}

}
