package edu.vortx3735.MyPlugin;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import edu.wpi.first.shuffleboard.api.data.MapData;
import edu.wpi.first.shuffleboard.api.data.types.MapType;
import edu.wpi.first.shuffleboard.api.widget.Description;
import edu.wpi.first.shuffleboard.api.widget.ParametrizedController;
import edu.wpi.first.shuffleboard.api.widget.SimpleAnnotatedWidget;
import eu.hansolo.medusa.Gauge;
import javafx.beans.property.SimpleListProperty;
import javafx.beans.property.SimpleSetProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableArrayBase;
import javafx.fxml.FXML;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;

@Description(dataTypes = { MapType.class }, name = "My Map Bound Widget")
@ParametrizedController(value = "MyMapBoundWidget.fxml")
public class MyMapBoundWidget extends SimpleAnnotatedWidget<MapData> implements ChangeListener<MapData> {

	@FXML
	private AnchorPane _thePane;

	@FXML
	private Gauge _topGauge;

	@FXML
	private Gauge _middleGauge;

	@FXML
	private Gauge _bottomGauge;

	private final SimpleStringProperty _topKey = new SimpleStringProperty(this, "topGaugeKey", "");
	private final SimpleStringProperty _middleKey = new SimpleStringProperty(this, "middleGaugeKey", "");
	private final SimpleStringProperty _bottomKey = new SimpleStringProperty(this, "bottomGaugeKey", "");
	
	
	public MyMapBoundWidget ()
	{
		// Export the properties to set the key value to bind to each gauge.
		exportProperties(_topKey, _middleKey, _bottomKey);
		
		// Set up a listener that gets triggered every time the map is updated.
		dataProperty().addListener(this);
	}

	
	public String getTopGaugeKey ()
	{
		return _topKey.getValue();
	}
	
	public void setTopGaugeKey (String top)
	{
		_topKey.setValue(top);
	}
	
	public String getMiddleGaugeKey ()
	{
		return _middleKey.getValue();
	}
	
	public void setMiddleGaugeKey (String middle)
	{
		_middleKey.setValue(middle);
	}
	

	public String getBottomGaugeKey ()
	{
		return _bottomKey.getValue();
	}
	
	public void setBottomGaugeKey (String bottom)
	{
		_bottomKey.setValue(bottom);
	}
	
	

	@Override
	public Pane getView() {

		return _thePane;
	}

	
	/**
	 * 
	 * @param g The gauge control to set the value in.
	 * @param key The key value from the map to find the data to set in the gauge.
	 */
	private void setValue (Gauge g, String key)
	{
		if (key != null && !key.isEmpty() && dataProperty().get().get(key) != null)
			g.valueProperty().set((double) dataProperty().get().get(key));
	}
	
	@Override
	public void changed(ObservableValue<? extends MapData> arg0, MapData arg1, MapData arg2) {
		
		// Invoked when the map changes.  It gets the value from the map by the key value,
		// then updates the appropriate gauge.
		
		setValue (_topGauge, _topKey.getValue());
		setValue (_middleGauge, _middleKey.getValue());
		setValue (_bottomGauge, _bottomKey.getValue());

	}

}
