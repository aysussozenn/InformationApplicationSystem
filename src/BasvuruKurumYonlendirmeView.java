import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BasvuruKurumYonlendirmeView implements ViewInterface {

	@Override
	public ViewData create(ModelData modelData, String functionName, String operationName) throws Exception {
		
		switch(operationName) {
		case "insert1": return insertOperation1(modelData);
		case "update1": return updateOperation1(modelData);	
		case "update1.gui": return update1GUI(modelData);
		case "insert1.gui" : return insert1GUI(modelData);

		}
		
		return new ViewData("MainMenu", "");
	}
	ViewData insertOperation1(ModelData modelData) throws Exception {
		System.out.println("Baþvurunuz seçtiðiniz kuruma yönlendirilmiþtir. Baþvurunuzun durumunu Kullanýcý Menüsünden öðrenebilirsiniz.");
		
		return new ViewData("MainMenu", "");
	}



	ViewData insert1GUI(ModelData modelData) throws Exception {
		Map<String, Object> parameters = new HashMap<>();
		parameters.put("fieldname", "BasvuruNo,KurumNo");

		List<Object> rows = new ArrayList<>();
	
		Integer basvuruNo,kurumNo;
	
			System.out.println("Baþvuracaðýnýz kurumu seçmek için lütfen aþaðýdaki bilgileri eksiksiz doldurunuz:\n");
			basvuruNo = getInteger("Basvuru No: ",true);
			kurumNo=getInteger("Kurum No", true);

			
			System.out.println();
					
			
				rows.add(new BasvuruKurumYonlendirme(basvuruNo,kurumNo));
			
		parameters.put("rows", rows);
		
		return new ViewData("BasvuruKurumYonlendirme", "insert1", parameters);
	}


	Map<String, Object> getWhereParameters() throws Exception {
		System.out.println("Seçimleri filtrele");
		System.out.println("------------------\n");
		Integer basvuruNo = getInteger("Basvuru No: ", true);
		
		Map<String, Object> whereParameters = new HashMap<>();
		if (basvuruNo != null) whereParameters.put("BasvuruNo",basvuruNo);

		return whereParameters;
	}
	
	//Baþvurunun iletileceði kurumun deðiþtirilebilmesi için çýkan ekran.
	ViewData update1GUI(ModelData modelData) throws Exception {
		System.out.println("Kurumlararasý yönlendirmenin tamamlanabilmesi için aþaðýdaki bilgileri eksiksiz doldurunuz:\n");
		
		
		//= getString("Soyad : ", true);
		System.out.println();
		Integer basvuruNo = getInteger("Baþvuru No: ", true);
		Integer kurumNo = getInteger("Kurum No: ", true);
		Map<String, Object> updateParameters = new HashMap<>();
	
		if (basvuruNo != 0) updateParameters.put("BasvuruNo", basvuruNo);
		if (kurumNo != null) updateParameters.put("KurumNo",kurumNo );
		
		Map<String, Object> parameters = new HashMap<>();
		parameters.put("updateParameters", updateParameters);
		parameters.put("whereParameters", getWhereParameters());
		
		return new ViewData("BasvuruKurumYonlendirme", "update1", parameters);
	}
	
	//Baþvurunun kurumlararasý iletilebilmesi için gerekli olan update'in operation'ý
	ViewData updateOperation1(ModelData modelData) throws Exception {
		System.out.println("Kurumlararasý yönlendirme baþarýyla tamamlanmýþtýr. ");
		
		return new ViewData("MainMenu", "");
	}

	@Override
	public String toString() {
		return " View";
	}		
}
