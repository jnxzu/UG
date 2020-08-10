using System;
using System.Collections.Generic;

namespace Gauss_Matrix_Equations
{
    public class Gauss<T> where T : new()
    {
        public static List<(int a, int b)> swaps = new List<(int, int)> { };
        public static T[] Calculate(MyMatrix<T> matrix)
        {
            var results = new T[matrix.Rows];
            for (var i = matrix.Rows - 1; i >= 0; i--)
            {
                var dzielnik = (dynamic)matrix.Matrix[i, i];
                for (var k = i; k < matrix.Cols; k++)
                {
                    matrix.Matrix[i, k] /= dzielnik;
                }
                for (var j = i - 1; j >= 0; j--)
                {
                    var mnoznik = (dynamic)matrix.Matrix[j,i];
                    for (var k = i; k < matrix.Cols; k++)
                    {
                        matrix.Matrix[j,k]-=(dynamic)matrix.Matrix[i,k]*(dynamic)mnoznik;
                    }
                }
            }
            for(var i =0;i<matrix.Rows;i++){
                results[i]=(dynamic)matrix.Matrix[i,matrix.Cols-1];
            }
            return results;
        }

        public static T[] RegularElimination(MyMatrix<T> matrix)
        {
            for (var i = 0; i < matrix.Rows - 1; i++)
            {
                for (var j = i + 1; j < matrix.Rows; j++)
                {
                    T mnoznik = (dynamic)matrix.Matrix[j, i] / (dynamic)matrix.Matrix[i, i];
                    for (var k = 0; k < matrix.Cols; k++)
                    {
                        matrix.Matrix[j, k] -= (dynamic)matrix.Matrix[i, k] * mnoznik;
                    }
                }
            }
            return Calculate(matrix);
        }

        public static MyMatrix<T> PartialPivot(MyMatrix<T> matrix, int idx)
        {
            var index = idx;
            for (var i = idx; i < matrix.Rows; i++)
            {
                if (matrix.Matrix.GetType() == typeof(Fraction[,]))
                {
                    if (Fraction.Abs((dynamic)matrix.Matrix[i, idx]) > Fraction.Abs((dynamic)matrix.Matrix[index, idx]))
                    {
                        index = i;
                    }
                }
                else
                {
                    if (Math.Abs((dynamic)matrix.Matrix[i, idx]) > Math.Abs((dynamic)matrix.Matrix[index, idx]))
                    {
                        index = i;
                    }
                }
                if (idx != index) matrix.SwapRows(idx, index);
            }
            return matrix;
        }

        public static T[] PartialPivotElimination(MyMatrix<T> matrix)
        {
            for (var i = 0; i < matrix.Rows - 1; i++)
            {
                matrix = PartialPivot(matrix, i);
                for (var j = i + 1; j < matrix.Rows; j++)
                {
                    T mnoznik = (dynamic)matrix.Matrix[j, i] / (dynamic)matrix.Matrix[i, i];
                    for (var k = 0; k < matrix.Cols; k++)
                    {
                        matrix.Matrix[j, k] -= (dynamic)matrix.Matrix[i, k] * mnoznik;
                    }
                }
            }
            return Calculate(matrix);
        }

        public static MyMatrix<T> FullPivot(MyMatrix<T> matrix, int idx)
        {
            var xindex = idx;
            var yindex = idx;
            for (var i = idx; i < matrix.Rows; i++)
            {
                for (var j = idx; j < matrix.Rows; j++)
                {
                    if (matrix.Matrix.GetType() == typeof(Fraction[,]))
                    {
                        if (Fraction.Abs((dynamic)matrix.Matrix[yindex, xindex]) < Fraction.Abs((dynamic)matrix.Matrix[i, j]))
                        {
                            yindex = i;
                            xindex = j;
                        }
                    }
                    else
                    {
                        if (Math.Abs((dynamic)matrix.Matrix[yindex, xindex]) < Math.Abs((dynamic)matrix.Matrix[i, j]))
                        {
                            yindex = i;
                            xindex = j;
                        }
                    }
                    if (idx != yindex) matrix.SwapRows(idx, yindex);
                    if (idx != xindex)
                    {
                        matrix.SwapCols(idx, xindex);
                        swaps.Add((idx, xindex));
                    };
                }
            }
            return matrix;
        }
        public static T[] FullPivotElimination(MyMatrix<T> matrix)
        {
            swaps.Clear();
            for (var i = 0; i < matrix.Rows - 1; i++)
            {
                matrix = FullPivot(matrix, i);
                for (var j = i + 1; j < matrix.Rows; j++)
                {
                    T mnoznik = (dynamic)matrix.Matrix[j, i] / (dynamic)matrix.Matrix[i, i];
                    for (var k = 0; k < matrix.Cols; k++)
                    {
                        matrix.Matrix[j, k] -= (dynamic)matrix.Matrix[i, k] * mnoznik;
                    }
                }
            }
            var results = Calculate(matrix);
            swaps.Reverse();
            foreach ((int a, int b) swap in swaps)
            {
                var tmp = results[swap.a];
                results[swap.a] = results[swap.b];
                results[swap.b] = tmp;
            }
            return results;
        }
    }
}