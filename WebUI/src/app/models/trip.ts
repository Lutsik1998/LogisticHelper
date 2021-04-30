export class Trip {
    id: number;
    customer : string;
    dateOfLoading : Date | string;
    dateOfUnLoading : Date | string;
    startPoint : string;
    destinationPoint : string;
    distance : number;
    price : number;
    priceWithoutVAT : number;
    vat : number;
    dfConsumed : number;
    driversSalary : number;
    truckNumber : string;
    pricePerKm : number;
    expenses : number;
    netProfit : number;
    dieselPrice : number;

}
