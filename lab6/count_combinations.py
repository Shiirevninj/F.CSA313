from itertools import product

from_values = ["Valid", "Frozen", "Missing"]
to_values = ["Valid", "Missing", "SameAsFrom"]
amount_values = [
    "Zero",
    "Negative",
    "Normal",
    "OverBalance",
    "OverLimit"
]
currency_values = ["Same", "Different"]

combinations = list(product(
    from_values,
    to_values,
    amount_values,
    currency_values
))

print("N =", len(combinations))

def is_valid(row):
    from_acc, to_acc, amount, currency = row

    # SameAsFrom нь хүчинтэй эх данстай үед утгатай.
    if to_acc == "SameAsFrom" and from_acc != "Valid":
        return False

    # Эх данс байхгүй үед үлдэгдэл шалгах боломжгүй.
    if from_acc == "Missing" and amount in [
        "OverBalance", "OverLimit"
    ]:
        return False

    return True

valid_combinations = [
    row for row in combinations if is_valid(row)
]

print("M =", len(valid_combinations))
print(f"N → M: {len(combinations)} → {len(valid_combinations)}")
