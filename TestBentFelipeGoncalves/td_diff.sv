`include "diff_pkg.sv"
module top;
  localparam int INPUT_WIDTH = 4;

  logic clk;
  logic reset;

  valid_ready_if #(.DATA_WIDTH(INPUT_WIDTH)) input_if(.clk(clk), .reset(reset));
  valid_ready_if #(.DATA_WIDTH(INPUT_WIDTH + 1)) output_if(.clk(clk), .reset(reset));
  diff #(.INPUT_WIDTH(INPUT_WIDTH)) dut(.out(output_if), .in(input_if));

  initial begin
    clk = 1'b0;
    reset = 1'b1;
    #5 clk = 1'b1;
    reset <= 1'b0;
    forever #5 clk = !clk;
  end
endmodule

