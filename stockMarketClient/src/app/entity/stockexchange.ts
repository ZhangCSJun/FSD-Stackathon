export class StockExchange {
    constructor(
        public id: string,
        public abbrname: string,
        public fullname: string,
        public brief: string,
        public contactAddress: string,
        public remark: string,
        public checked: boolean
    ){ }
}