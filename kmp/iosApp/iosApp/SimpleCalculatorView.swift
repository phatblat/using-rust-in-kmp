import SwiftUI
import Shared


struct SimpleCalculatorView: View {
    @State private var num1: String = ""
    @State private var num2: String = ""
    @State private var result: String = ""

    var body: some View {
        VStack(spacing:
 16) {
            TextField("Number 1", text: $num1)
                .textFieldStyle(RoundedBorderTextFieldStyle())
                .padding(.horizontal)

            TextField("Number 2", text: $num2)
                .textFieldStyle(RoundedBorderTextFieldStyle())
                .padding(.horizontal)

            Button("Calculate") {
                let n1 = Int32(num1) ?? 0
                let n2 = Int32(num2) ?? 0
                // Invoke Calc.ios's add function
                let sum = Calc_iosKt.add(a: n1, b: n2)
                result = String(sum)
            }
            .padding(.horizontal)

            Text("Result: \(result)")
                .font(.title3)
        }
        .padding()
    }
}

struct SimpleCalculatorView_Previews: PreviewProvider {
    static var previews: some View {
        SimpleCalculatorView()
    }
}
