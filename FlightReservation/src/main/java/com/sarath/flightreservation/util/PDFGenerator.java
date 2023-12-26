package com.sarath.flightreservation.util;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import com.sarath.flightreservation.entities.Reservation;

public class PDFGenerator 
{
	public void generatateItinary(Reservation reservation, String filePath)
	{
		Document doc = new Document();
		try 
		{
			PdfWriter.getInstance(doc, new FileOutputStream(filePath));
			doc.open();
			doc.add(generateTable(reservation));
			doc.close();
		} 
		catch (FileNotFoundException | DocumentException e) 
		{
			e.printStackTrace();
		}
	}

	private PdfPTable generateTable(Reservation reservation) 
	{
		PdfPTable table = new PdfPTable(2);
		
		PdfPCell cell;
		cell = new PdfPCell(new Phrase("Flight Itinerary"));
		cell.setColspan(2);
		table.addCell(cell);
		
		cell = new PdfPCell(new Phrase("Flight Details"));
		cell.setColspan(2);
		table.addCell(cell);
		
		table.addCell("Airlines");
		table.addCell(reservation.getFlight().getOperating_airlines());
		
		table.addCell("Departure City");
		table.addCell(reservation.getFlight().getDeparture_city());
		
		table.addCell("Arival City");
		table.addCell(reservation.getFlight().getArrival_city());
		
		table.addCell("Flight No");
		table.addCell(reservation.getFlight().getFlight_number());
		
		table.addCell("Departure Time");
		table.addCell(reservation.getFlight().getEstimate_departure_time().toString());
		
		table.addCell("Departure Date");
		table.addCell(reservation.getFlight().getDate_of_departure().toString());
		
		table.addCell("Arival City");
		table.addCell(reservation.getFlight().getArrival_city());
		
		cell = new PdfPCell(new Phrase("Passanger Details"));
		cell.setColspan(2);
		table.addCell(cell);
		
		table.addCell("First Name");
		table.addCell(reservation.getPassenger().getFirstName());
		
		table.addCell("Last Name");
		table.addCell(reservation.getPassenger().getLastName());
		
		table.addCell("Email");
		table.addCell(reservation.getPassenger().getEmail());
		
		table.addCell("Phone No");
		table.addCell(reservation.getPassenger().getPhone());
		
		return table;
	}

}
