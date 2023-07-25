package org.example.bank_api;

import org.example.exception.CurrencyNotFoundException;
import org.example.exception.NotSupportedCurrencyForThisDate;
import org.example.utils.Currency;

import org.example.utils.CurrencyCurrentDate;
import org.example.utils.DateTransformer;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.print.Doc;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Date;

public class CentralBankApiRunner {
    private String CharSetCB = "Cp1251";

    public Currency findCurrencyByIsoCode(String currencyCode) {
        String urlForListWithCurrencies = "https://www.cbr.ru/scripts/XML_valFull.asp";

        String xmlData = getXMLDataFromURL(urlForListWithCurrencies);
        Document document = parseToDocument(xmlData);

        return findCurrencyFromList(document, currencyCode);
    }

    private Currency findCurrencyFromList(Document document, String currencyCode) {
        NodeList itemList = document.getElementsByTagName("Item");
        for (int i = 0; i < itemList.getLength(); i++) {
            Element item = (Element) itemList.item(i);
            String isoCode = item.getElementsByTagName("ISO_Char_Code").item(0).getTextContent();

            if (isoCode.equals(currencyCode)) {
                String name = item.getElementsByTagName("Name").item(0).getTextContent();
                String id = item.getElementsByTagName("ParentCode").item(0).getTextContent();
                return new Currency(name, currencyCode, id);
            }
        }

        throw new CurrencyNotFoundException();
    }

    public CurrencyCurrentDate getCurrencyValueFromCurrentDate(Date date, Currency currency) {
        String dateOf = DateTransformer.getDateToCBFormat(date);
        // creating url with ID and date(same date at start and finish range to get info about only one day)
        String baseurl = "https://www.cbr.ru/scripts/XML_dynamic.asp?";
        String urlForGetCurrencyWithNominalAndValueAtCurrentDate = baseurl + "date_req1=" + dateOf + "&date_req2=" + dateOf + "&VAL_NM_RQ=" + currency.getId();

        String xmlData = getXMLDataFromURL(urlForGetCurrencyWithNominalAndValueAtCurrentDate);
        Document document = parseToDocument(xmlData);

        CurrencyCurrentDate result = extractCurrencyForCurrentDate(document);
        result.setDate(date);
        result.setCurrency(currency);

        return result;
    }

    private CurrencyCurrentDate extractCurrencyForCurrentDate(Document document) {
        Element record = (Element) document.getElementsByTagName("Record").item(0);

        if (record == null) {
            throw new NotSupportedCurrencyForThisDate();
        }

        Double nominal = Double.valueOf(record.getElementsByTagName("Nominal").item(0).getTextContent());
        Double value = Double.valueOf(record.getElementsByTagName("Value").item(0).getTextContent().replace(',', '.'));

        CurrencyCurrentDate result = new CurrencyCurrentDate();
        result.setNominal(nominal);
        result.setValue(value);
        return result;
    }

    private String getXMLDataFromURL(String urlStr) {
        try {
            StringBuilder response = new StringBuilder();
            URL url = new URL(urlStr);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();

            if (connection.getResponseCode() == HttpURLConnection.HTTP_OK) {
                BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream(), CharSetCB));

                String line;
                while ((line = reader.readLine()) != null) {
                    response.append(line);
                }

                reader.close();
                connection.disconnect();
            } else {
                System.out.println("Error with fetching data from URL. Response code: " + connection.getResponseCode());
            }

            return response.toString();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private Document parseToDocument(String data) {
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder;
        try {
            builder = factory.newDocumentBuilder();
            return builder.parse(new ByteArrayInputStream(data.getBytes(CharSetCB)));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }


}
