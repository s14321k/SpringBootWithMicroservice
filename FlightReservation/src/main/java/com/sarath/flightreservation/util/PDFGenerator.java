package com.sarath.flightreservation.util;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.springframework.stereotype.Component;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import com.sarath.flightreservation.entities.Reservation;

@Component
public class PDFGenerator 
{
	public String generatateItinary(Reservation reservation, Long id)
	{
		String folderName = "reservtionPDF"+id;
		
		String folderPath = "D:/";
		
		String fullPath = folderPath + folderName;
		
		Path path = Paths.get(fullPath);
		
		if(!Files.exists(path))
		{
			try
			{
				Files.createDirectories(path);
				System.out.println("Folder created \n :P :P :P ))))))))))))))))))))) \n EEEEEEEEEEEEEEEEEEE \n Folder created   ---------->");
			}
			catch(IOException e)
			{
				e.printStackTrace();
			}
		}
		else
		{
			System.out.println("Folder already exists");
		}
		
		Document doc = new Document();
		
		String pdfPath = fullPath+"/reservation"+id+".pdf";
		try 
		{
			PdfWriter.getInstance(doc, new FileOutputStream(pdfPath));
			doc.open();
			doc.add(generateTable(reservation));
			doc.close();
		} 
		catch (FileNotFoundException | DocumentException e) 
		{
			e.printStackTrace();
		}
		
		return pdfPath;
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
