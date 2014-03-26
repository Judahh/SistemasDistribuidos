/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package View;

/**
 *
 * @subAuthor Name <e-mail>
 * @author Judah Holanda Correia Lima <judahholanda7@gmail.com>
 */
public class CompactorInformation {
   private final String size;
   private final String quantity;
   private final String type;
   private final String way;
   private long totalTime;
   private double averageTime;
   private long maxTime;
   private long minTime;

   public CompactorInformation(String size, String quantity, String type, String way) {
      this.size = size;
      this.quantity = quantity;
      this.type = type;
      this.way = way;
   }

   public double getAverageTime() {
      return averageTime;
   }

   public void setAverageTime(double averageTime) {
      this.averageTime = averageTime;
   }

   public void setTotalTime(long totalTime) {
      this.totalTime = totalTime;
   }

   public long getTotalTime() {
      return totalTime;
   }
   
   public long getMaxTime() {
      return maxTime;
   }

   public void setMaxTime(long maxTime) {
      this.maxTime = maxTime;
   }

   public long getMinTime() {
      return minTime;
   }

   public void setMinTime(long minTime) {
      this.minTime = minTime;
   }

   public String getSize() {
      return size;
   }

   public String getQuantity() {
      return quantity;
   }

   public String getType() {
      return type;
   }

   public String getWay() {
      return way;
   }
   
   
}
