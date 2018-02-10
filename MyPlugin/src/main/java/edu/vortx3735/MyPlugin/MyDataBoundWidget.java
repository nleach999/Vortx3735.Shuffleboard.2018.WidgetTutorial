package edu.vortx3735.MyPlugin;

import edu.wpi.first.shuffleboard.api.data.types.NumberType;
import edu.wpi.first.shuffleboard.api.widget.Description;
import edu.wpi.first.shuffleboard.api.widget.ParametrizedController;
import edu.wpi.first.shuffleboard.api.widget.SimpleAnnotatedWidget;
import eu.hansolo.medusa.Gauge;
import javafx.fxml.FXML;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;

@Description(dataTypes = { NumberType.class }, name = "My First Data Bound Widget")
@ParametrizedController(value = "MyDataBoundWidget.fxml")
public class MyDataBoundWidget extends SimpleAnnotatedWidget<Number> {

	@FXML
	private AnchorPane _thePane;

	@FXML
	Gauge _theGauge;

	@Override
	public Pane getView() {

		_theGauge.valueProperty().bind(dataProperty());

		exportProperties(_theGauge.maxValueProperty(), _theGauge.minValueProperty(), _theGauge.valueVisibleProperty(),
				_theGauge.tickLabelOrientationProperty(), _theGauge.tickLabelColorProperty(),
				_theGauge.tickLabelLocationProperty(), _theGauge.animatedProperty(), _theGauge.tickMarkColorProperty(),
				_theGauge.needleShapeProperty(), _theGauge.needleSizeProperty(), _theGauge.needleTypeProperty(),
				_theGauge.majorTickMarkTypeProperty(), _theGauge.minorTickMarkTypeProperty());

		return _thePane;
	}

}
