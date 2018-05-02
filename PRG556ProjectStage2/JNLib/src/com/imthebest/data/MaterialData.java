//Nam Nguyen
package com.imthebest.data;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import ca.senecacollege.prg556.limara.bean.Material;
import ca.senecacollege.prg556.limara.dao.MaterialDAO;

class MaterialData implements MaterialDAO{

	@Override
	public List<Material> getAvailableMaterials(String title, String type)
			throws SQLException {
		
	List<Material> materials = new ArrayList<Material>();
	
	materials.add (getMaterial(title));
	materials.add (getMaterial(type));
	
		return materials;
	}

	@Override
	public Material getMaterial(String materialId) throws SQLException {
			String title_1 = "abc";
			String title_2 = "asd";
			String title_3 = "qwe";
			String id_1 = "123";
			String id_2 = "456";
			String id_3 = "789";
			String type_1 = "Book";
			String type_2 = "Audio";
			String type_3 = "Video";
	
		
	Material m1 = new Material (id_1, title_1, type_1);
	Material m2 = new Material (id_2, title_2, type_2);
	Material m3 = new Material (id_3, title_3, type_3);
	
			if (id_1.equals(materialId))
				{
					return  m1;
				}
			else if (id_2.equals(materialId))
				{
					return m2;
				}
			else if (id_3.equals(materialId))
				{
					return m3;
				}
			else
		return null;
	}

}
