using System;

namespace Gauss_Matrix_Equations
{
    public class MyMatrix<T>
    {
        static int high = 65535;
        static int low = -65536;
        public T[,] Matrix { get; set; }
        public int Rows { get; set; }
        public int Cols { get; set; }

        public MyMatrix(int size)
        {
            Rows = size;
            Cols = size;
            Matrix = new T[Rows, Cols];
        }

        public MyMatrix(int rows, int cols)
        {
            Rows = rows;
            Cols = cols;
            Matrix = new T[Rows, Cols];
        }

        public T[] MultiplyVector(T[] vector)
        {
            T[] result = new T[Rows];
            for (var i = 0; i < Rows; i++)
            {
                for (var j = 0; j < Rows; j++)
                {
                    result[i] += (dynamic)Matrix[i, j] * (dynamic)vector[j];
                }
            }
            return result;
        }

        public Fraction[] MultiplyVectorF(T[] vector)
        {
            Fraction[] result = new Fraction[Rows];
            for (var i = 0; i < Rows; i++)
            {
                result[i] = new Fraction(0,1);
                for (var j = 0; j < Rows; j++)
                {
                    result[i] += (dynamic)Matrix[i, j] * (dynamic)vector[j];
                }
            }
            return result;
        }

        public MyMatrix<T> CombineVector(T[] vector)
        {
            MyMatrix<T> result = new MyMatrix<T>(Rows, Cols + 1);
            for (var i = 0; i < result.Rows; i++)
            {
                for (var j = 0; j < result.Cols; j++)
                {
                    result.Matrix[i, j] = j == result.Cols - 1 ? vector[i] : Matrix[i, j];
                }
            }
            return result;
        }

        public void SwapRows(int rowA, int rowB)
        {
            T tmp;
            for (var i = 0; i < Cols; i++)
            {
                tmp = Matrix[rowA, i];
                Matrix[rowA, i] = Matrix[rowB, i];
                Matrix[rowB, i] = tmp;
            }
        }

        public void SwapCols(int colA, int colB)
        {
            T tmp;
            for (var i = 0; i < Rows; i++)
            {
                tmp = Matrix[i, colA];
                Matrix[i, colA] = Matrix[i, colB];
                Matrix[i, colB] = tmp;
            }
        }
        public static float RandomFloat(Random rand)
        {
            var val = (float)rand.Next(low, high);
            return val / high;
        }
        public static double RandomDouble(Random rand)
        {
            var val = (double)rand.Next(low, high);
            return val / high;
        }
        public static Fraction RandomFraction(Random rand)
        {
            var val = rand.Next(1, 21);
            return new Fraction(val, 21);
        }
        public static dynamic RandomValue(Type t, Random rand)
        {
            if (t == typeof(float))
                return RandomFloat(rand);
            else if (t == typeof(double))
                return RandomDouble(rand);
            else if (t == typeof(Fraction))
                return RandomFraction(rand);
            else
                return null;
        }
        public void Generate(Random rand)
        {
            for (var i = 0; i < Rows; i++)
            {
                for (var j = 0; j < Cols; j++)
                {
                    Matrix[i, j] = RandomValue(typeof(T), rand);
                }
            }
        }

        public static T[] GenerateVector(Random rand, int size)
        {
            T[] results = new T[size];
            for (var i = 0; i < size; i++)
            {
                results[i] = RandomValue(typeof(T), rand);
            }
            return results;
        }
    }
}