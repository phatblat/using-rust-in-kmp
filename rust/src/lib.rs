use consts::PI;
use std::f64::consts;

#[unsafe(no_mangle)]
pub extern "C" fn add(left: i32, right: i32) -> i32 {
    left + right
}

enum Shape {
    Point,
    Circle(f64),
    Rect { width: f64, height: f64 },
}

impl Shape {
    fn area(&self) -> f64 {
        match self {
            Shape::Point => 0.0,
            Shape::Circle(radius) => PI * radius * radius,
            Shape::Rect { width, height } => width * height,
        }
    }
}

fn main() {
    let shape = Shape::Circle(1.2);
    let area = shape.area();
    println!("the area is {area}");
}

#[cfg(test)]
mod tests {
    use super::*;

    #[test]
    fn it_works() {
        let result = add(2, 2);
        assert_eq!(result, 4);
    }

    #[allow(unconditional_panic)]
    fn divide_by_zero() {
        1 / 0;
    }
}
