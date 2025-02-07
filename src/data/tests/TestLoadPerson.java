//package data.tests;
//
//import data.DataManager;
//import data.Entities.Adresse;
//import data.Entities.Geschlecht;
//import data.Entities.Person;
//
//public class TestLoadPerson {
//
//	public static void main(String[] args) {
//
//		Person p = new Person("Anne");
//
//		System.out.println("ID der Person vorm Speichern: " + p.getId());
//
//		DataManager dm = DataManager.getInstance();
//		dm.save(p);
//
//		System.out.println("ID der Person nachm Speichern: " + p.getId());
//		int id = p.getId();
//
//		p = dm.loadPersonById(id);
//		System.out.println("Person mit dem Namen: " + p.getVorname() + "wurde geladen");
//	}
//}