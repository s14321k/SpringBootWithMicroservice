package com.sarath.location.util;


import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartUtilities;
import org.jfree.chart.JFreeChart;
import org.jfree.data.general.DefaultPieDataset;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Locale;

@Component
public class ReportUtilImpl implements ReportUtil
{

    @Override
    public void generatePieChart(String path, List<Object[]> data)
    {
        DefaultPieDataset dataset = new DefaultPieDataset();

        for (Object[] obj : data)
        {
            //new Double is  depricated since java 9
            //dataset.setValue(obj[0].toString(),new Double(obj[1].toString()));
            dataset.setValue(obj[0].toString(),Double.valueOf(obj[1].toString()));


            //Creating into  jfreechart object
            JFreeChart jFreeChart = ChartFactory.createPieChart3D("Location Type Report", dataset, true , true, true);


            //Converting into jpeg file
            try
            {
                ChartUtilities.saveChartAsJPEG(new File(path+"/pieChart.jpeg"), jFreeChart, 300, 300);
            }
            catch (IOException e)
            {
                e.printStackTrace();
            }
        }
    }
}
