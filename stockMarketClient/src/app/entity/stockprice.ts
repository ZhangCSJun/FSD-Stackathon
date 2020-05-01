export class StockPrice {
    constructor(
        public companyCode: string,
        public stockCode: string,
        public stockExchange: string,
        public currentPrice: number,
        public currency: string,
        public date: string,
        public time: string,
    ){ }
}