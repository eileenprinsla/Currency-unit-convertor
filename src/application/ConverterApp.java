package application;
	
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;


public class ConverterApp extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Unit and Currency Converter");

        GridPane grid = new GridPane();
        grid.setAlignment(Pos.CENTER);
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(25, 25, 25, 25));
        grid.setStyle("-fx-background-color: #ADD8E6;");

        // Unit Converter
        Label unitLabel = new Label("Unit Converter");
        grid.add(unitLabel, 0, 0, 2, 1);

        TextField unitInput = new TextField();
        grid.add(unitInput, 0, 1);

        Label unitFromLabel = new Label("From:");
        grid.add(unitFromLabel, 1, 1);

        ChoiceBox<String> unitFrom = new ChoiceBox<>();
        unitFrom.getItems().addAll("Meter", "Kilometer", "Mile", "Centimeter", "Foot", "Inch", "Yard", "Kilogram", "Gram", "Milligram", "Pound", "Ounce", "Celsius", "Fahrenheit", "Kelvin");
        unitFrom.setValue("Meter");
        grid.add(unitFrom, 2, 1);

        Label unitToLabel = new Label("To:");
        grid.add(unitToLabel, 3, 1);

        ChoiceBox<String> unitTo = new ChoiceBox<>();
        unitTo.getItems().addAll("Meter", "Kilometer", "Mile", "Centimeter", "Foot", "Inch", "Yard", "Kilogram", "Gram", "Milligram", "Pound", "Ounce", "Celsius", "Fahrenheit", "Kelvin");
        unitTo.setValue("Meter");
        grid.add(unitTo, 4, 1);

        Button unitConvertBtn = new Button("Convert");
        grid.add(unitConvertBtn, 5, 1);

        TextField unitResultField = new TextField();
        unitResultField.setEditable(false);
        grid.add(unitResultField, 6, 1);

        // Currency Converter
        Label currencyLabel = new Label("Currency Converter");
        grid.add(currencyLabel, 0, 2, 2, 1);

        TextField currencyInput = new TextField();
        grid.add(currencyInput, 0, 3);

        Label currencyFromLabel = new Label("From:");
        grid.add(currencyFromLabel, 1, 3);

        ChoiceBox<String> currencyFrom = new ChoiceBox<>();
        currencyFrom.getItems().addAll("USD", "EUR", "GBP","JPY","CHF","CAD","AUD","NZD","CNY","INR","BRL","ZAR","RUB","MXN","SGD","HKD","NOK","SEK","KRW","TRY");
        currencyFrom.setValue("USD");
        grid.add(currencyFrom, 2, 3);

        Label currencyToLabel = new Label("To:");
        grid.add(currencyToLabel, 3, 3);

        ChoiceBox<String> currencyTo = new ChoiceBox<>();
        currencyTo.getItems().addAll("USD", "EUR", "GBP","JPY","CHF","CAD","AUD","NZD","CNY","INR","BRL","ZAR","RUB","MXN","SGD","HKD","NOK","SEK","KRW","TRY");
        currencyTo.setValue("USD");
        grid.add(currencyTo, 4, 3);

        Button currencyConvertBtn = new Button("Convert");
        grid.add(currencyConvertBtn, 5, 3);

        TextField currencyResultField = new TextField();
        currencyResultField.setEditable(false);
        grid.add(currencyResultField, 6, 3);

        Label resultLabel = new Label("Result:");
        grid.add(resultLabel, 0, 5);

        TextField resultField = new TextField();
        resultField.setEditable(false);
        grid.add(resultField, 1, 5, 6, 1);

        unitConvertBtn.setOnAction(e -> {
            try {
                double value = Double.parseDouble(unitInput.getText());
                String fromUnit = unitFrom.getValue();
                String toUnit = unitTo.getValue();
                double result = convertUnits(value, fromUnit, toUnit);

                if (!Double.isNaN(result)) {
                    unitResultField.setText(String.format("%.2f %s", result, toUnit));
                    resultField.setText(String.format("%.2f %s  to  %s %s", value, fromUnit, result, toUnit));
                } else {
                    unitResultField.setText("Conversion not supported");
                }

            } catch (NumberFormatException ex) {
                unitResultField.setText("Invalid input");
            }
        });

        currencyConvertBtn.setOnAction(e -> {
            try {
                double amount = Double.parseDouble(currencyInput.getText());
                String fromCurrency = currencyFrom.getValue();
                String toCurrency = currencyTo.getValue();
                double result = convertCurrency(amount, fromCurrency, toCurrency);

                if (!Double.isNaN(result)) {
                    currencyResultField.setText(String.format("%.2f %s", result, toCurrency));
                    resultField.setText(String.format("%.2f %s  to  %s %s", amount, fromCurrency, result, toCurrency));
                } else {
                    currencyResultField.setText("Conversion not supported");
                }

            } catch (NumberFormatException ex) {
                currencyResultField.setText("Invalid input");
            }
        });

        Scene scene = new Scene(grid, 800, 500);
        primaryStage.setScene(scene);

        primaryStage.show();
    }

    private double convertUnits(double value, String fromUnit, String toUnit) {
        switch (fromUnit) {
            case "Meter":
                switch (toUnit) {
                    case "Kilometer":
                        return value * 0.001;
                    case "Meter":
                        return value ;
                    case "Mile":
                        return value / 1609.34;
                    case "Millimeter":
                        return value * 1000.0;
                    case "Centimeter":
                        return value * 100.0;
                    case "Yard":
                        return value * 1.09361;
                    case "Foot":
                        return value * 3.28084;
                    case "Inch":
                        return value * 39.3701;
                    default:
                        return Double.NaN; // Conversion not possible
                }
            case "Kilometer":
                switch (toUnit) {
                    case "Meter":
                        return value * 1000.0;
                    case "Kilometer":
                        return value ;
                    case "Mile":
                        return value / 1.60934;
                    case "Millimeter":
                        return value * 1_000_000.0;
                    case "Centimeter":
                        return value * 100_000.0;
                    case "Yard":
                        return value * 1094.0;
                    case "Foot":
                        return value * 3281.0;
                    case "Inch":
                        return value * 39_370.1;
                    default:
                        return Double.NaN; // Conversion not possible
                }
            case "Millimeter":
                switch (toUnit) {
                    case "Meter":
                        return value / 1000.0;
                    case "Kilometer":
                        return value / 1_000_000.0;
                    case "Millimeter":
                        return value ;
                    case "Centimeter":
                        return value / 10.0;
                    case "Mile":
                        return value / 1_609_340.0;
                    case "Yard":
                        return value / 914.4;
                    case "Foot":
                        return value / 304.8;
                    case "Inch":
                        return value / 25.4;
                    default:
                        return Double.NaN; // Conversion not possible
                }
            case "Mile":
                switch (toUnit) {
                    case "Meter":
                        return value * 1609.34;
                    case "Kilometer":
                        return value * 1.60934;
                    case "Mile":
                        return value ;
                    case "Millimeter":
                        return value * 1_609_340.0;
                    case "Centimeter":
                        return value * 160_934.0;
                    case "Yard":
                        return value * 1760.0;
                    case "Foot":
                        return value * 5280.0;
                    case "Inch":
                        return value * 63_360.0;
                    default:
                        return Double.NaN; // Conversion not possible
                }
            case "Centimeter":
                switch (toUnit) {
                    case "Meter":
                        return value / 100.0;
                    case "Kilometer":
                        return value / 100_000.0;
                    case "Centimeter":
                        return value ;
                    case "Millimeter":
                        return value * 10.0;
                    case "Mile":
                        return value / 160_934.0;
                    case "Yard":
                        return value / 91.44;
                    case "Foot":
                        return value / 30.48;
                    case "Inch":
                        return value / 2.54;
                    default:
                        return Double.NaN; // Conversion not possible
                }
            case "Foot":
                switch (toUnit) {
                    case "Meter":
                        return value / 3.28084;
                    case "Kilometer":
                        return value / 3280.84;
                    case "Foot":
                        return value ;
                    case "Millimeter":
                        return value * 304.8;
                    case "Mile":
                        return value / 5280.0;
                    case "Yard":
                        return value / 3.0;
                    case "Inch":
                        return value * 12.0;
                    case "Centimeter":
                        return value * 30.48;
                    default:
                        return Double.NaN; // Conversion not possible
                }
            case "Yard":
                switch (toUnit) {
                    case "Meter":
                        return value / 1.09361;
                    case "Kilometer":
                        return value / 1093.61;
                    case "Yard":
                        return value ;
                    case "Millimeter":
                        return value * 914.4;
                    case "Mile":
                        return value / 1760.0;
                    case "Foot":
                        return value * 3.0;
                    case "Inch":
                        return value * 36.0;
                    case "Centimeter":
                        return value * 91.44;
                    default:
                        return Double.NaN; // Conversion not possible
                }
            case "Inch":
                switch (toUnit) {
                    case "Meter":
                        return value / 39.3701;
                    case "Kilometer":
                        return value / 39370.1;
                    case "Inch":
                        return value ;
                    case "Millimeter":
                        return value * 25.4;
                    case "Mile":
                        return value / 63360.0;
                    case "Yard":
                        return value / 36.0;
                    case "Foot":
                        return value / 12.0;
                    case "Centimeter":
                        return value * 2.54;
                    default:
                        return Double.NaN; // Conversion not possible
                }
            case "Kilogram":
                switch (toUnit) {
                    case "Gram":
                        return value * 1000.0;
                    case "Kilogram":
                        return value ;
                    case "Pound":
                        return value * 2.20462;
                    case "Ounce":
                        return value * 35.274;
                    default:
                        return Double.NaN; // Conversion not possible
                }
            case "Gram":
                switch (toUnit) {
                    case "Kilogram":
                        return value / 1000.0;
                    case "Gram":
                        return value ;
                    case "Pound":
                        return value / 453.592;
                    case "Ounce":
                        return value / 28.3495;
                    default:
                        return Double.NaN; // Conversion not possible
                }
            case "Pound":
                switch (toUnit) {
                    case "Kilogram":
                        return value / 2.20462;
                    case "Pound":
                        return value ;
                    case "Gram":
                        return value * 453.592;
                    case "Ounce":
                        return value * 16.0;
                    default:
                        return Double.NaN; // Conversion not possible
                }
            case "Ounce":
                switch (toUnit) {
                    case "Gram":
                        return value * 28.3495;
                    case "Ounce":
                        return value ;
                    case "Pound":
                        return value / 16.0;
                    default:
                        return Double.NaN; // Conversion not possible
                }
            case "Kelvin":
                switch (toUnit) {
                    case "Celsius":
                        return value - 273.15;
                    case "Kelvin":
                        return value ;
                    case "Fahrenheit":
                        return (value - 273.15) * 9 / 5 +32;
                    default:
                        return Double.NaN; // Conversion not possible;
                }
            case "Fahrenheit":
                switch (toUnit) {
                    case "Celsius":
                        return (value - 32) * 5/9;
                    case "Fahrenheit":
                        return value ;
                    case "Kelvin":
                        return (value - 32) * 5/9 + 273.15;
                    default:
                        return Double.NaN; // Conversion not possible;
                }
            case "Celsius":
                switch (toUnit) {
                    case "Fahrenheit":
                        return value * 9/5 + 32;
                    case "Celsius":
                        return value ;
                    case "Kelvin":
                        return value + 273.15;
                    default:
                        return Double.NaN; // Conversion not possible;
                }
            default:
                return Double.NaN; // Conversion not possible;
        }
    }

    

    private double convertCurrency(double amount, String fromCurrency, String toCurrency) {
        

        switch (fromCurrency) {
        case "USD":
            switch (toCurrency)
            {
            case "USD":
            	return amount * 1;
            case "EUR":
            	return 0.90 * amount;
            case "GBP":
                return amount * 0.78;
            case "TRY":
            	return amount * 29.44; 
            case "KRW":
            	return amount * 1286.89;
            case "SEK":
            	return amount * 9.92;
            case "NOK":
            	return amount * 10.1295;
            case "HKD":
            	return amount * 7.81;
            case "SGD":
            	return amount * 1.32;
            case "MXN":
            	return amount * 16.90;
            case "RUB":
            	return amount * 90.68;
            case "ZAR":
            	return amount * 18.33;
            case "BRL":
            	return amount * 4.83;
            case "INR":
            	return amount * 83.25;
            case "CNY":
            	return amount * 7.06;
            case "NZD":
            	return amount * 1.58;
            case "AUD":
            	return amount * 1.46;
            case "CAD":
            	return amount * 1.32;
            case "CHF":
            	return amount * 0.84;
            case "JPY":
            	return amount * 140.73;
            
            }
        case "EUR":
        	switch (toCurrency)
            {
            case "USD":
            	return amount * 1;
            case "EUR":
            	return 0.90 * amount;
            case "GBP":
                return amount * 0.78;
            case "TRY":
            	return amount * 29.44; 
            case "KRW":
            	return amount * 1286.89;
            case "SEK":
            	return amount * 9.92;
            case "NOK":
            	return amount * 10.07;
            case "HKD":
            	return amount * 7.81;
            case "SGD":
            	return amount * 1.32;
            case "MXN":
            	return amount * 16.90;
            case "RUB":
            	return amount * 90.68;
            case "ZAR":
            	return amount * 18.33;
            case "BRL":
            	return amount * 4.83;
            case "INR":
            	return amount * 83.25;
            case "CNY":
            	return amount * 7.06;
            case "NZD":
            	return amount * 1.58;
            case "AUD":
            	return amount * 1.46;
            case "CAD":
            	return amount * 1.32;
            case "CHF":
            	return amount * 0.84;
            case "JPY":
            	return amount * 140.73;
            
            }
        case "GBP":
        	switch (toCurrency)
            {
            case "USD":
            	return amount * 1;
            case "EUR":
            	return 0.90 * amount;
            case "GBP":
                return amount * 0.78;
            case "TRY":
            	return amount * 29.44; 
            case "KRW":
            	return amount * 1286.89;
            case "SEK":
            	return amount * 9.92;
            case "NOK":
            	return amount * 10.07;
            case "HKD":
            	return amount * 7.81;
            case "SGD":
            	return amount * 1.32;
            case "MXN":
            	return amount * 16.90;
            case "RUB":
            	return amount * 90.68;
            case "ZAR":
            	return amount * 18.33;
            case "BRL":
            	return amount * 4.83;
            case "INR":
            	return amount * 83.25;
            case "CNY":
            	return amount * 7.06;
            case "NZD":
            	return amount * 1.58;
            case "AUD":
            	return amount * 1.46;
            case "CAD":
            	return amount * 1.32;
            case "CHF":
            	return amount * 0.84;
            case "JPY":
            	return amount * 140.73;
            
            }
        case "TRY":
        	switch (toCurrency)
            {
            case "USD":
            	return amount * 1;
            case "EUR":
            	return 0.90 * amount;
            case "GBP":
                return amount * 0.78;
            case "TRY":
            	return amount * 29.44; 
            case "KRW":
            	return amount * 1286.89;
            case "SEK":
            	return amount * 9.92;
            case "NOK":
            	return amount * 10.07;
            case "HKD":
            	return amount * 7.81;
            case "SGD":
            	return amount * 1.32;
            case "MXN":
            	return amount * 16.90;
            case "RUB":
            	return amount * 90.68;
            case "ZAR":
            	return amount * 18.33;
            case "BRL":
            	return amount * 4.83;
            case "INR":
            	return amount * 83.25;
            case "CNY":
            	return amount * 7.06;
            case "NZD":
            	return amount * 1.58;
            case "AUD":
            	return amount * 1.46;
            case "CAD":
            	return amount * 1.32;
            case "CHF":
            	return amount * 0.84;
            case "JPY":
            	return amount * 140.73;
            
            }
        case "KRW":
        	switch (toCurrency)
            {
            case "USD":
            	return amount * 0.001 ;
            case "EUR":
            	return  0.001 * amount;
            case "GBP":
                return amount * 0.001;
            case "TRY":
            	return amount * 0.0229102; 
            case "KRW":
            	return amount * 1;
            case "SEK":
            	return amount * 0.008;
            case "NOK":
            	return amount * 0.00787042;
            case "HKD":
            	return amount * 0.006;
            case "SGD":
            	return amount * 0.001;
            case "MXN":
            	return amount * 0.0131416;
            case "RUB":
            	return amount * 0.0698302;
            case "ZAR":
            	return amount * 0.014;
            case "BRL":
            	return amount * 0.004;
            case "INR":
            	return amount * 0.065;
            case "CNY":
            	return amount * 0.006;
            case "NZD":
            	return amount * 0.001;
            case "AUD":
            	return amount * 0.001 ;
            case "CAD":
            	return amount * 0.001 ;
            case "CHF":
            	return amount * 0.001 ;
            case "JPY":
            	return amount * 0.109;
            
            }
        case "SEK":
        	switch (toCurrency)
            {
            case "USD":
            	return amount * 0.10;
            case "EUR":
            	return  0.091 * amount;
            case "GBP":
                return amount * 0.079;
            case "TRY":
            	return amount * 2.96; 
            case "KRW":
            	return amount * 129.49;
            case "SEK":
            	return amount * 8.38;
            case "NOK":
            	return amount * 1.02;
            case "HKD":
            	return amount * 0.79;
            case "SGD":
            	return amount *0.13 ;
            case "MXN":
            	return amount * 1.70;
            case "RUB":
            	return amount *9.11 ;
            case "ZAR":
            	return amount * 1.86;
            case "BRL":
            	return amount * 0.49;
            case "INR":
            	return amount *8.38 ;
            case "CNY":
            	return amount * 0.71;
            case "NZD":
            	return amount * 0.16;
            case "AUD":
            	return amount *0.15 ;
            case "CAD":
            	return amount * 0.13;
            case "CHF":
            	return amount *0.084 ;
            case "JPY":
            	return amount *14.19 ;
            
            }
            
        case "NOK":
        	switch (toCurrency)
            {
            case "USD":
            	return amount * 0.099;
            case "EUR":
            	return  0.08913* amount;
            case "GBP":
                return amount *0.077 ;
            case "TRY":
            	return amount *2.92 ; 
            case "KRW":
            	return amount * 127.46;
            case "SEK":
            	return amount *0.98 ;
            case "NOK":
            	return amount * 8.25;
            case "HKD":
            	return amount * 0.77;
            case "SGD":
            	return amount * 0.13;
            case "MXN":
            	return amount *1.68 ;
            case "RUB":
            	return amount *8.97 ;
            case "ZAR":
            	return amount * 1.83;
            case "BRL":
            	return amount *0.48 ;
            case "INR":
            	return amount *8.25 ;
            case "CNY":
            	return amount *0.70 ;
            case "NZD":
            	return amount *0.16 ;
            case "AUD":
            	return amount * 0.14;
            case "CAD":
            	return amount * 0.13;
            case "CHF":
            	return amount * 0.083;
            case "JPY":
            	return amount * 13.97;
            
            }
        case "HKD":
        	switch (toCurrency)
            {
            case "USD":
            	return amount * 0.13;
            case "EUR":
            	return 0.12 * amount;
            case "GBP":
                return amount *0.100 ;
            case "TRY":
            	return amount *3.77 ; 
            case "KRW":
            	return amount *164.37 ;
            case "SEK":
            	return amount * 1.27;
            case "NOK":
            	return amount *1.29 ;
            case "HKD":
            	return amount *10.65 ;
            case "SGD":
            	return amount *0.17 ;
            case "MXN":
            	return amount *2.17 ;
            case "RUB":
            	return amount *11.58 ;
            case "ZAR":
            	return amount *2.36 ;
            case "BRL":
            	return amount * 0.62;
            case "INR":
            	return amount *10.65 ;
            case "CNY":
            	return amount * 0.90;
            case "NZD":
            	return amount * 0.02;
            case "AUD":
            	return amount * 0.19;
            case "CAD":
            	return amount *0.17 ;
            case "CHF":
            	return amount *0.11 ;
            case "JPY":
            	return amount *18.04 ;
            
            }
        case "SGD":
        	switch (toCurrency)
            {
            case "USD":
            	return amount * 0.76;
            case "EUR":
            	return  0.68* amount;
            case "GBP":
                return amount *0.59 ;
            case "TRY":
            	return amount * 22.36; 
            case "KRW":
            	return amount *975.49 ;
            case "SEK":
            	return amount *7.53 ;
            case "NOK":
            	return amount *7.67 ;
            case "HKD":
            	return amount *5.93 ;
            case "SGD":
            	return amount * 63.20;
            case "MXN":
            	return amount * 12.85;
            case "RUB":
            	return amount * 68.73;
            case "ZAR":
            	return amount *14.11 ;
            case "BRL":
            	return amount *4.83 ;
            case "INR":
            	return amount * 63.20;
            case "CNY":
            	return amount * 5.37;
            case "NZD":
            	return amount *1.20 ;
            case "AUD":
            	return amount *1.11 ;
            case "CAD":
            	return amount *1.00 ;
            case "CHF":
            	return amount * 0.64;
            case "JPY":
            	return amount *140.91 ;
            
            }
        case "MXN":
        	switch (toCurrency)
            {
            case "USD":
            	return amount *0.059 ;
            case "EUR":
            	return  0.053 * amount;
            case "GBP":
                return amount * 0.046;
            case "TRY":
            	return amount *1.74 ; 
            case "KRW":
            	return amount *76.02 ;
            case "SEK":
            	return amount * 0.59 ;
            case "NOK":
            	return amount *0.60 ;
            case "HKD":
            	return amount * 0.46;
            case "SGD":
            	return amount *0.078 ;
            case "MXN":
            	return amount * 4.92;
            case "RUB":
            	return amount * 5.35;
            case "ZAR":
            	return amount *1.09 ;
            case "BRL":
            	return amount *0.29 ;
            case "INR":
            	return amount *4.92 ;
            case "CNY":
            	return amount *0.42 ;
            case "NZD":
            	return amount * 0.093;
            case "AUD":
            	return amount *0.086 ;
            case "CAD":
            	return amount *0.078 ;
            case "CHF":
            	return amount *0.050 ;
            case "JPY":
            	return amount *8.33 ;
            
            
            }
        case "RUB":
        	switch (toCurrency)
            {
            case "USD":
            	return amount *0.011 ;
            case "EUR":
            	return  0.0099 * amount;
            case "GBP":
                return amount *0.0086 ;
            case "TRY":
            	return amount *0.33 ; 
            case "KRW":
            	return amount *14.18 ;
            case "SEK":
            	return amount *0.11 ;
            case "NOK":
            	return amount * 0.11;
            case "HKD":
            	return amount * 0.086;
            case "SGD":
            	return amount * 0.015;
            case "MXN":
            	return amount * 0.19;
            case "RUB":
            	return amount * 1.09;
            case "ZAR":
            	return amount * 0.20;
            case "BRL":
            	return amount * 0.053;
            case "INR":
            	return amount * 0.92;
            case "CNY":
            	return amount * 0.078;
            case "NZD":
            	return amount * 0.017;
            case "AUD":
            	return amount * 0.016;
            case "CAD":
            	return amount *0.015 ;
            case "CHF":
            	return amount *0.0093 ;
            case "JPY":
            	return amount * 1.56;
            
            }
        case "ZAR":
        	switch (toCurrency)
            {
            case "USD":
            	return amount * 0.05405;
            case "EUR":
            	return  0.04882* amount;
            case "GBP":
                return amount * 0.04238;
            case "TRY":
            	return amount * 1.58669; 
            case "KRW":
            	return amount * 69.9152;
            case "SEK":
            	return amount * 0.53894;
            case "NOK":
            	return amount * 0.54751;
            case "HKD":
            	return amount * 0.42215;
            case "SGD":
            	return amount * 0.07144;
            case "MXN":
            	return amount * 0.91608;
            case "RUB":
            	return amount * 4.95188;
            case "ZAR":
            	return amount * 1;
            case "BRL":
            	return amount * 0.26052;
            case "INR":
            	return amount * 4.50101;
            case "CNY":
            	return amount *0.38595 ;
            case "NZD":
            	return amount * 0.08534;
            case "AUD":
            	return amount *0.07904 ;
            case "CAD":
            	return amount * 0.07135;
            case "CHF":
            	return amount * 0.04592;
            case "JPY":
            	return amount * 7.69397;
            
            }
        case "BRL":
        	switch (toCurrency)
            {
            case "USD":
            	return amount * 0.20732 ;
            case "EUR":
            	return  0.18726* amount;
            case "GBP":
                return amount * 0.16255;
            case "TRY":
            	return amount * 6.08587; 
            case "KRW":
            	return amount * 268.166;
            case "SEK":
            	return amount * 2.06716;
            case "NOK":
            	return amount * 2.10003;
            case "HKD":
            	return amount * 1.61919;
            case "SGD":
            	return amount * 0.274;
            case "MXN":
            	return amount * 3.5137;
            case "RUB":
            	return amount *18.9934;
            case "ZAR":
            	return amount * 3.83253;
            case "BRL":
            	return amount * 1;
            case "INR":
            	return amount * 17.264;
            case "CNY":
            	return amount * 1.48037;
            case "NZD":
            	return amount * 0.32732;
            case "AUD":
            	return amount * 0.30318;
            case "CAD":
            	return amount * 0.27366;
            case "CHF":
            	return amount * 0.17614;
            case "JPY":
            	return amount * 29.5109;
            
            }
        case "INR":
        	switch (toCurrency)
            {
            case "USD":
            	return amount * 0.01201;
            case "EUR":
            	return  0.01085 * amount;
            case "GBP":
                return amount *0.00941 ;
            case "TRY":
            	return amount * 0.35249; 
            case "KRW":
            	return amount * 15.5322;
            case "SEK":
            	return amount * 0.11973;
            case "NOK":
            	return amount * 0.12163;
            case "HKD":
            	return amount * 0.09378;
            case "SGD":
            	return amount * 0.01587;
            case "MXN":
            	return amount * 0.20351;
            case "RUB":
            	return amount * 1.1001;
            case "ZAR":
            	return amount * 0.22198;
            case "BRL":
            	return amount * 0.05788;
            case "INR":
            	return amount *1 ;
            case "CNY":
            	return amount * 0.08574;
            case "NZD":
            	return amount *0.01896 ;
            case "AUD":
            	return amount * 0.01756;
            case "CAD":
            	return amount * 0.01585;
            case "CHF":
            	return amount * 0.0102;
            case "JPY":
            	return amount * 1.70927;
            
            }
        case "CNY":
        	switch (toCurrency)
            {
            case "USD":
            	return amount * 0.13999;
            case "EUR":
            	return  0.12645* amount;
            case "GBP":
                return amount * 0.10976;
            case "TRY":
            	return amount * 4.10935; 
            case "KRW":
            	return amount * 181.073;
            case "SEK":
            	return amount * 1.3958;
            case "NOK":
            	return amount * 1.418;
            case "HKD":
            	return amount * 1.09332;
            case "SGD":
            	return amount * 0.18501;
            case "MXN":
            	return amount * 2.37255;
            case "RUB":
            	return amount * 12.8248;
            case "ZAR":
            	return amount * 2.58783;
            case "BRL":
            	return amount * 0.67473;
            case "INR":
            	return amount * 11.6571;
            case "CNY":
            	return amount * 1;
            case "NZD":
            	return amount * 0.22101;
            case "AUD":
            	return amount * 0.20472;
            case "CAD":
            	return amount * 0.18478;
            case "CHF":
            	return amount * 0.11894;
            case "JPY":
            	return amount * 19.9266;
            
            }
        case "NZD":
        	switch (toCurrency)
            {
            case "USD":
            	return amount * 0.63323;
            case "EUR":
            	return  0.57197* amount;
            case "GBP":
                return amount * 0.49649;
            case "TRY":
            	return amount * 18.5885; 
            case "KRW":
            	return amount * 819.078;
            case "SEK":
            	return amount * 6.31387;
            case "NOK":
            	return amount * 6.41428;
            case "HKD":
            	return amount * 4.94561;
            case "SGD":
            	return amount * 0.83689;
            case "MXN":
            	return amount * 10.7321;
            case "RUB":
            	return amount * 58.0128;
            case "ZAR":
            	return amount * 11.706;
            case "BRL":
            	return amount *3.0521;
            case "INR":
            	return amount * 52.7307;
            case "CNY":
            	return amount * 4.52159;
            case "NZD":
            	return amount * 1;
            case "AUD":
            	return amount * 0.92607;
            case "CAD":
            	return amount * 0.83586;
            case "CHF":
            	return amount * 0.538;
            case "JPY":
            	return amount * 90.1372;
            
            }
        case "AUD":
        	switch (toCurrency)
            {
            case "USD":
            	return amount * 0.69;
            case "EUR":
            	return  0.62 * amount;
            case "GBP":
                return amount * 0.53;
            case "TRY":
            	return amount * 20.17; 
            case "KRW":
            	return amount * 882.16;
            case "SEK":
            	return amount * 6.80;
            case "NOK":
            	return amount * 0.60;
            case "HKD":
            	return amount * 5.35;
            case "SGD":
            	return amount * 0.90;
            case "MXN":
            	return amount * 11.59;
            case "RUB":
            	return amount * 61.4676 ;
            case "ZAR":
            	return amount * 12.58;
            case "BRL":
            	return amount * 4.83;
            case "INR":
            	return amount * 56.98;
            case "CNY":
            	return amount * 4.84;
            case "NZD":
            	return amount * 1.08;
            case "AUD":
            	return amount * 1;
            case "CAD":
            	return amount * 0.90;
            case "CHF":
            	return amount * 0.57;
            case "JPY":
            	return amount * 96.46;
            
            }
        case "CAD":
        	switch (toCurrency)
            {
            case "USD":
            	return amount * 1;
            case "EUR":
            	return 0.68 * amount;
            case "GBP":
                return amount * 0.59;
            case "TRY":
            	return amount * 22.32; 
            case "KRW":
            	return amount * 976.22;
            case "SEK":
            	return amount * 7.52;
            case "NOK":
            	return amount * 7.65;
            case "HKD":
            	return amount * 5.92;
            case "SGD":
            	return amount * 1.00;
            case "MXN":
            	return amount * 16.91;
            case "RUB":
            	return amount * 69.3939;
            case "ZAR":
            	return amount * 13.90;
            case "BRL":
            	return amount * 3.66;
            case "INR":
            	return amount * 63.09;
            case "CNY":
            	return amount * 5.37;
            case "NZD":
            	return amount * 1.19;
            case "AUD":
            	return amount * 1.11;
            case "CAD":
            	return amount * 1;
            case "CHF":
            	return amount * 0.64;
            case "JPY":
            	return amount * 140.73;
            
            }
        case "CHF":
        	switch (toCurrency)
            {
            case "USD":
            	return amount * 1.189;
            case "EUR":
            	return 1.071 * amount;
            case "GBP":
                return amount * 0.929;
            case "TRY":
            	return amount * 35.066; 
            case "KRW":
            	return amount * 1533.850;
            case "SEK":
            	return amount * 11.817;
            case "NOK":
            	return amount * 12.014;
            case "HKD":
            	return amount * 9.296;
            case "SGD":
            	return amount * 1.568;
            case "MXN":
            	return amount * 20.132;
            case "RUB":
            	return amount * 107.806;
            case "ZAR":
            	return amount * 21.826;
            case "BRL":
            	return amount * 5.754;
            case "INR":
            	return amount * 99.141;
            case "CNY":
            	return amount * 8.463;
            case "NZD":
            	return amount * 1.876;
            case "AUD":
            	return amount * 1.738;
            case "CAD":
            	return amount * 1.570;
            case "CHF":
            	return amount * 1;
            case "JPY":
            	return amount * 167.803;
            
            }
        	case "JPY":
        		switch (toCurrency)
                {
                case "USD":
                	return amount * 0.007;
                case "EUR":
                	return 0.006 * amount;
                case "GBP":
                    return amount * 0.006;
                case "TRY":
                	return amount * 0.209; 
                case "KRW":
                	return amount * 9.125;
                case "SEK":
                	return amount * 0.070;
                case "NOK":
                	return amount * 0.071;
                case "HKD":
                	return amount * 0.055;
                case "SGD":
                	return amount * 0.009;
                case "MXN":
                	return amount * 0.120 ;
                case "RUB":
                	return amount * 0.64352;
                case "ZAR":
                	return amount * 0.130;
                case "BRL":
                	return amount * 0.034;
                case "INR":
                	return amount * 0.589;
                case "CNY":
                	return amount * 0.050;
                case "NZD":
                	return amount * 0.011;
                case "AUD":
                	return amount * 0.010;
                case "CAD":
                	return amount * 0.009;
                case "CHF":
                	return amount * 0.006;
                case "JPY":
                	return amount * 1;
                
                }
        }
		return amount;
    }
    }
