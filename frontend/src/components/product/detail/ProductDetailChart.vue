<script>
import { Line } from "vue3-chart-v2";

export default {
  extends: Line,
  data() {
    return {
      datacollection: {
        //Data to be represented on x-axis
        labels: [
          "January",
          "February",
          "March",
          "April",
          "May",
          "June",
          "July",
          "August",
          "September",
        ],
        datasets: [
          {
            label: "Data One",
            pointBackgroundColor: "white",
            fill: false,
            borderWidth: 1,
            borderColor: "#cf000f",
            // pointBorderColor: "#249EBF",
            //Data to be represented on y-axis
            data: [40, 20, 30, 50, 60, 10, 20, 40, 50],
          },
        ],
      },
      //Chart.js options that controls the appearance of the chart
      options: {
        responsive: true,
        scales: {
          yAxes: [
            {
              ticks: {
                beginAtZero: true,
                suggestedMax: 90, // y축 최대값
                display: false,   // y축 라벨 표시
              },
              gridLines: {
                display: true,
              },
            },
          ],
          xAxes: [
            {
              gridLines: {
                display: false,
              },
            },
          ],
        },
        legend: {
          // datasets label 보이게, 안보이게
          display: false,
        },
        maintainAspectRatio: false,
      },
    };
  },
  mounted() {
    //renderChart function renders the chart with the datacollection and options object.
    this.renderChart(this.datacollection, this.options);

    // https://ohgyun.com/323
    var canvas = document.querySelector('canvas');
    canvas.height = 250;
    canvas.width = 600;
    canvas.style.height = '250px';
    canvas.style.width = '600px';
  },
};
</script>
