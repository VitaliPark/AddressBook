package model;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.commons.fileupload.FileItem;

public class DefaultParameterContainer implements ParameterContainer{
	private Map<String, String[]> params;

	public DefaultParameterContainer(List<FileItem> inputItems) {

		super();
		params = new HashMap<String, String[]>();
		init(inputItems);
	}

	private void init(List<FileItem> inputItems) {

		Map<String, List<String>> map = new HashMap<String, List<String>>();
		for (FileItem item : inputItems) {
			if (item.isFormField()) {
				List<String> currentValues = map.get(item.getFieldName());
				if (currentValues == null) {
					currentValues = new ArrayList<String>();
					map.put(item.getFieldName(), currentValues);
				}
				try {
					currentValues.add(item.getString("UTF-8"));
				} catch (UnsupportedEncodingException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		for (Entry<String, List<String>> parameter : map.entrySet()) {
			List<String> valueList = parameter.getValue();
			this.params.put(parameter.getKey(),
					valueList.toArray(new String[valueList.size()]));
		}
	}

	@Override
	public String getParameter(String key) {

		String[] values = this.params.get(key);
		return (values == null) ? null : values[0];
	}

	@Override
	public String[] getParameterValues(String key) {

		String[] values = this.params.get(key);
		String[] output = null;
		if (values != null) {
			output = Arrays.copyOf(values, values.length);
		}
		return output;
	}
}
