package ingredient_gui;

import java.util.Comparator;

import com.ibm.watson.developer_cloud.visual_recognition.v3.model.ClassResult;

public class ClassifierIdSort implements Comparator<ClassResult> {
	
	public int compare(ClassResult cr1, ClassResult cr2) {
		return cr1.getScore().compareTo(cr2.getScore());
	}

}
