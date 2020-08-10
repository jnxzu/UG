using System;
using System.Diagnostics;
using System.IO;

namespace Gauss_Matrix_Equations
{
    class Program
    {
        static double Difference<T>(T[] pattern, T[] result)
        {
            double diff = 0;
            if (result.GetType() == typeof(Fraction[]))
            {
                for (var i = 0; i < pattern.Length; i++)
                {
                    Fraction frc = Fraction.Abs((dynamic)pattern[i] - (dynamic)result[i]);
                    diff += ((double)frc.top / (double)frc.bot) * ((double)frc.top / (double)frc.bot);
                }
                return diff;
            }
            else
            {
                for (var i = 0; i < pattern.Length; i++)
                {
                    diff += Math.Abs((dynamic)pattern[i] - (dynamic)result[i]) * Math.Abs((dynamic)pattern[i] - (dynamic)result[i]);
                }
                return diff;
            }
        }

        static void WriteToFIle(int size, string method, string type, double error, double time)
        {
            string line = size.ToString() + ";" + method + ";" + type + ";" + error.ToString().Replace(',', '.') + ";" + time.ToString().Replace(',', '.');
            using (FileStream fs = new FileStream("results.csv", FileMode.Append, FileAccess.Write))
            {
                using (StreamWriter sw = new StreamWriter(fs))
                {
                    sw.WriteLine(line);
                }
            }
        }
        static void TestRegular(int size, int matrixseed, int vectorseed)
        {
            var floatMatrix = new MyMatrix<float>(size);
            var doubleMatrix = new MyMatrix<double>(size);
            var fractionMatrix = new MyMatrix<Fraction>(size);

            floatMatrix.Generate(new Random(matrixseed));
            doubleMatrix.Generate(new Random(matrixseed));
            fractionMatrix.Generate(new Random(matrixseed));

            var floatVector = MyMatrix<float>.GenerateVector(new Random(vectorseed), size);
            var doubleVector = MyMatrix<double>.GenerateVector(new Random(vectorseed), size);
            var fractionVector = MyMatrix<Fraction>.GenerateVector(new Random(vectorseed), size);

            var floatResult = floatMatrix.MultiplyVector(floatVector);
            var doubleResult = doubleMatrix.MultiplyVector(doubleVector);
            var fractionResult = fractionMatrix.MultiplyVectorF(fractionVector);

            floatMatrix = floatMatrix.CombineVector(floatResult);
            doubleMatrix = doubleMatrix.CombineVector(doubleResult);
            fractionMatrix = fractionMatrix.CombineVector(fractionResult);

            var sw = new Stopwatch();

            // sw.Start();
            // var floatCalculatedRegular = Gauss<float>.RegularElimination(floatMatrix);
            // sw.Stop();
            // var floatRegularTime = sw.Elapsed.TotalMilliseconds;
            // sw.Reset();
            // var floatRegularDiff = Difference(floatVector, floatCalculatedRegular);
            // WriteToFIle(size, "Regular", "Float", floatRegularDiff, floatRegularTime);

            // sw.Start();
            // var doubleCalculatedRegular = Gauss<double>.RegularElimination(doubleMatrix);
            // sw.Stop();
            // var doubleRegularTime = sw.Elapsed.TotalMilliseconds;
            // sw.Reset();
            // var doubleRegularDiff = Difference(doubleVector, doubleCalculatedRegular);
            // WriteToFIle(size, "Regular", "Double", doubleRegularDiff, doubleRegularTime);

            sw.Start();
            var fractionCalculatedRegular = Gauss<Fraction>.RegularElimination(fractionMatrix);
            sw.Stop();
            var fractionRegularTime = sw.Elapsed.TotalMilliseconds;
            sw.Reset();
            var fractionRegularDiff = Difference(fractionVector, fractionCalculatedRegular);
            WriteToFIle(size, "Regular", "Fraction", fractionRegularDiff, fractionRegularTime);
        }

        static void TestPartial(int size, int matrixseed, int vectorseed)
        {
            var floatMatrix = new MyMatrix<float>(size);
            var doubleMatrix = new MyMatrix<double>(size);
            var fractionMatrix = new MyMatrix<Fraction>(size);

            floatMatrix.Generate(new Random(matrixseed));
            doubleMatrix.Generate(new Random(matrixseed));
            fractionMatrix.Generate(new Random(matrixseed));

            var floatVector = MyMatrix<float>.GenerateVector(new Random(vectorseed), size);
            var doubleVector = MyMatrix<double>.GenerateVector(new Random(vectorseed), size);
            var fractionVector = MyMatrix<Fraction>.GenerateVector(new Random(vectorseed), size);

            var floatResult = floatMatrix.MultiplyVector(floatVector);
            var doubleResult = doubleMatrix.MultiplyVector(doubleVector);
            var fractionResult = fractionMatrix.MultiplyVectorF(fractionVector);

            floatMatrix = floatMatrix.CombineVector(floatResult);
            doubleMatrix = doubleMatrix.CombineVector(doubleResult);
            fractionMatrix = fractionMatrix.CombineVector(fractionResult);

            var sw = new Stopwatch();

            // sw.Start();
            // var floatCalculatedPartial = Gauss<float>.PartialPivotElimination(floatMatrix);
            // sw.Stop();
            // var floatPartialTime = sw.Elapsed.TotalMilliseconds;
            // sw.Reset();
            // var floatPartialDiff = Difference(floatVector, floatCalculatedPartial);
            // WriteToFIle(size, "Partial Pivot", "Float", floatPartialDiff, floatPartialTime);

            // sw.Start();
            // var doubleCalculatedPartial = Gauss<double>.PartialPivotElimination(doubleMatrix);
            // sw.Stop();
            // var doublePartialTime = sw.Elapsed.TotalMilliseconds;
            // sw.Reset();
            // var doublePartialDiff = Difference(doubleVector, doubleCalculatedPartial);
            // WriteToFIle(size, "Partial Pivot", "Double", doublePartialDiff, doublePartialTime);

            sw.Start();
            var fractionCalculatedPartial = Gauss<Fraction>.PartialPivotElimination(fractionMatrix);
            sw.Stop();
            var fractionPartialTime = sw.Elapsed.TotalMilliseconds;
            sw.Reset();
            var fractionPartialDiff = Difference(fractionVector, fractionCalculatedPartial);
            WriteToFIle(size, "Partial Pivot", "Fraction", fractionPartialDiff, fractionPartialTime);
        }

        static void TestFull(int size, int matrixseed, int vectorseed)
        {
            var floatMatrix = new MyMatrix<float>(size);
            var doubleMatrix = new MyMatrix<double>(size);
            var fractionMatrix = new MyMatrix<Fraction>(size);

            floatMatrix.Generate(new Random(matrixseed));
            doubleMatrix.Generate(new Random(matrixseed));
            fractionMatrix.Generate(new Random(matrixseed));

            var floatVector = MyMatrix<float>.GenerateVector(new Random(vectorseed), size);
            var doubleVector = MyMatrix<double>.GenerateVector(new Random(vectorseed), size);
            var fractionVector = MyMatrix<Fraction>.GenerateVector(new Random(vectorseed), size);

            var floatResult = floatMatrix.MultiplyVector(floatVector);
            var doubleResult = doubleMatrix.MultiplyVector(doubleVector);
            var fractionResult = fractionMatrix.MultiplyVectorF(fractionVector);

            floatMatrix = floatMatrix.CombineVector(floatResult);
            doubleMatrix = doubleMatrix.CombineVector(doubleResult);
            fractionMatrix = fractionMatrix.CombineVector(fractionResult);

            var sw = new Stopwatch();

            sw.Start();
            var floatCalculatedFull = Gauss<float>.FullPivotElimination(floatMatrix);
            sw.Stop();
            var floatFullTime = sw.Elapsed.TotalMilliseconds;
            sw.Reset();
            var floatFullDiff = Difference(floatVector, floatCalculatedFull);
            WriteToFIle(size, "Full Pivot", "Float", floatFullDiff, floatFullTime);

            sw.Start();
            var doubleCalculatedFull = Gauss<double>.FullPivotElimination(doubleMatrix);
            sw.Stop();
            var doubleFullTime = sw.Elapsed.TotalMilliseconds;
            sw.Reset();
            var doubleFullDiff = Difference(doubleVector, doubleCalculatedFull);
            WriteToFIle(size, "Full Pivot", "Double", doubleFullDiff, doubleFullTime);

            sw.Start();
            var fractionCalculatedFull = Gauss<Fraction>.FullPivotElimination(fractionMatrix);
            sw.Stop();
            var fractionFullTime = sw.Elapsed.TotalMilliseconds;
            sw.Reset();
            var fractionFullDiff = Difference(fractionVector, fractionCalculatedFull);
            WriteToFIle(size, "Full Pivot", "Fraction", fractionFullDiff, fractionFullTime);
        }
        static void TestAll(int size, int matrixseed, int vectorseed)
        {
            TestRegular(size, matrixseed, vectorseed);
            TestPartial(size, matrixseed, vectorseed);
            TestFull(size, matrixseed, vectorseed);
        }
        static void Main(string[] args)
        {
            // var randM = new Random().Next();
            // var randV = new Random().Next();
            // TestAll(5, randM, randV);
            // TestAll(10, randM, randV);
            // TestAll(25, randM, randV);
            // TestAll(50, randM, randV);
            // TestAll(70, randM, randV);
            // TestAll(90, randM, randV);
            // TestAll(100, randM, randV);
            // TestAll(200, randM, randV);
            // TestAll(300, randM, randV);
            TestAll(400, 5, 4);
            TestAll(500, 5, 4);

            // var testMatrix = new MyMatrix<Fraction>(3);
            // testMatrix.Generate(new Random());
            // var testVector = MyMatrix<Fraction>.GenerateVector(new Random(), 3);
            // var testResult = testMatrix.MultiplyVectorF(testVector);
        }
    }
}
