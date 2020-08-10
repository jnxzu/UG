using System.Numerics;

namespace Gauss_Matrix_Equations
{
    public class Fraction
    {
        public BigInteger top { get; set; }
        public BigInteger bot { get; set; }

        public Fraction(BigInteger t, BigInteger b)
        {
            top = t;
            bot = b;
            Fix();
        }

        public Fraction()
        {
            top = 0;
            bot = 1;
        }

        public void Fix()
        {
            if (bot < 0)
            {
                top = BigInteger.Negate(top);
                bot = BigInteger.Negate(bot);
            }
            if (top == 0 || bot == 0)
            {
                top = 0;
                bot = 1;
            }
            BigInteger gcd = BigInteger.GreatestCommonDivisor(top, bot);
            top /= gcd;
            bot /= gcd;
        }

        public static Fraction operator +(Fraction a, Fraction b)
        {
            if (a.top == 0 && b.top != 0) return b;
            if (b.top == 0 && a.top != 0) return a;
            BigInteger gcd = BigInteger.GreatestCommonDivisor(a.bot, b.bot);
            BigInteger top = a.top * b.bot / gcd + b.top * a.bot / gcd;
            BigInteger bot = a.bot * b.bot / gcd;
            var result = new Fraction(top, bot);
            return result;
        }

        public static Fraction operator -(Fraction a)
        {
            if (a.top == 0) return a;
            return new Fraction(BigInteger.Negate(a.top), a.bot);
        }
        public static Fraction operator -(Fraction a, Fraction b)
        {
            return a + -b;
        }
        public static Fraction operator *(Fraction a, Fraction b)
        {
            var result = new Fraction(a.top * b.top, a.bot * b.bot);
            return result;
        }
        public static Fraction operator /(Fraction a, Fraction b)
        {
            var result = a * new Fraction(b.bot, b.top);
            return result;
        }

        public override bool Equals(object obj)
        {
            var frac = obj as Fraction;
            return (frac.bot == bot && frac.top == top);
        }

        public override int GetHashCode()
        {
            return (top / bot).GetHashCode();
        }

        public static bool operator >(Fraction a, Fraction b)
        {
            BigInteger gcd = BigInteger.GreatestCommonDivisor(a.bot, b.bot);
            Fraction result1 = new Fraction(a.top * (b.bot / gcd), a.bot * (b.bot / gcd));
            Fraction result2 = new Fraction(b.top * (a.bot / gcd), a.bot * (b.bot / gcd));
            return (result1.top > result2.top);
        }

        public static bool operator <(Fraction a, Fraction b)
        {
            BigInteger gcd = BigInteger.GreatestCommonDivisor(a.bot, b.bot);
            Fraction result1 = new Fraction(a.top * (b.bot / gcd), a.bot * (b.bot / gcd));
            Fraction result2 = new Fraction(b.top * (a.bot / gcd), a.bot * (b.bot / gcd));
            return (result1.top < result2.top);
        }

        public static bool operator ==(Fraction a, Fraction b)
        {
            BigInteger gcd = BigInteger.GreatestCommonDivisor(a.bot, b.bot);
            Fraction result1 = new Fraction(a.top * (b.bot / gcd), a.bot * (b.bot / gcd));
            Fraction result2 = new Fraction(b.top * (a.bot / gcd), a.bot * (b.bot / gcd));
            return (result1.top == result2.top);
        }

        public static bool operator !=(Fraction a, Fraction b)
        {
            BigInteger gcd = BigInteger.GreatestCommonDivisor(a.bot, b.bot);
            Fraction result1 = new Fraction(a.top * (b.bot / gcd), a.bot * (b.bot / gcd));
            Fraction result2 = new Fraction(b.top * (a.bot / gcd), a.bot * (b.bot / gcd));
            return (result1.top != result2.top);
        }
        public override string ToString()
        {
            Fix();
            return $"{top}/{bot}";
        }
        public static Fraction Abs(Fraction frc)
        {
            if (frc.top < 0) return new Fraction(BigInteger.Negate(frc.top), frc.bot);
            else return frc;
        }
    }
}