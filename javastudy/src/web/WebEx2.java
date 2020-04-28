package web;

import javax.swing.*;

import javafx.application.Platform;
import javafx.embed.swing.JFXPanel;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;

public class WebEx2 extends JFrame {
	
	public static void initAndLoadWebView(final JFXPanel fxPanel) {
		Group group = new Group();
		Scene scene = new Scene(group);
		fxPanel.setScene(scene);

		WebView webView = new WebView();

		group.getChildren().add(webView);
		webView.setMinSize(1000, 1000);
		webView.setMaxSize(1000, 1000);

		WebEngine webEngine = webView.getEngine();

		webEngine.load("file:///C:/html_study/slug/slug.html");
		
	}

}