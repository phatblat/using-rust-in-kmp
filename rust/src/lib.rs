#![feature(unsafe_attributes)]

#[unsafe(no_mangle)]
pub extern "C"
fn add_numbers(left: i32, right: i32) -> i32 {
    left + right
}

#[cfg(test)]
mod tests {
    use super::*;

    #[test]
    fn it_works() {
        let result = add_numbers(2, 2);
        assert_eq!(result, 4);
    }

    #[allow(unconditional_panic)]
    fn divide_by_zero() {
        1 / 0;
    }
}
