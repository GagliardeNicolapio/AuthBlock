package com.example.authblock.chain;

import java.math.BigInteger;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import org.web3j.abi.TypeReference;
import org.web3j.abi.datatypes.Function;
import org.web3j.abi.datatypes.Type;
import org.web3j.abi.datatypes.Utf8String;
import org.web3j.abi.datatypes.generated.Uint256;
import org.web3j.crypto.Credentials;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.core.RemoteCall;
import org.web3j.protocol.core.RemoteFunctionCall;
import org.web3j.protocol.core.methods.response.TransactionReceipt;
import org.web3j.tx.Contract;
import org.web3j.tx.TransactionManager;
import org.web3j.tx.gas.ContractGasProvider;

/**
 * <p>Auto generated code.
 * <p><strong>Do not modify!</strong>
 * <p>Please use the <a href="https://docs.web3j.io/command_line.html">web3j command line tools</a>,
 * or the org.web3j.codegen.SolidityFunctionWrapperGenerator in the 
 * <a href="https://github.com/web3j/web3j/tree/master/codegen">codegen module</a> to update.
 *
 * <p>Generated with web3j version 1.4.2.
 */
@SuppressWarnings("rawtypes")
public class Contracts_AuthBlockLite_sol_AuthBlockLite extends Contract {
    public static final String BINARY = "608060405234801561001057600080fd5b50610b5f806100206000396000f3fe60806040526004361061003f5760003560e01c806336c0adec146100445780634307199e1461006657806382c5577114610089578063c1422e001461009c575b600080fd5b34801561005057600080fd5b5061006461005f36600461061b565b6100c9565b005b34801561007257600080fd5b506000546040519081526020015b60405180910390f35b6100646100973660046106a8565b61024f565b3480156100a857600080fd5b506100bc6100b73660046107d3565b610341565b6040516100809190610812565b600060018260ff16116101235760405162461bcd60e51b815260206004820152601960248201527f20496c20766f746f2065272074726f70706f20626173736f200000000000000060448201526064015b60405180910390fd5b60068260ff16106101765760405162461bcd60e51b815260206004820152601760248201527f494c20766f746f206527206d6167696f72652064692035000000000000000000604482015260640161011a565b3360009081526003602052604090205460ff16156101e25760015460005461019e919061085b565b336000908152600360205260409020549091506101be9060ff1682610878565b6001549091506101d160ff84168361088b565b6101db919061089e565b6000555050565b336000908152600360205260408120805460ff191660ff8516908117909155600154915490916102119161085b565b61021b919061088b565b6000908155600180549161022e836108c0565b9190505550600154600080828254610246919061089e565b90915550505050565b6601c6bf526340003410156102955760405162461bcd60e51b815260206004820152600c60248201526b32b93937b91039b2b73232b960a11b604482015260640161011a565b60006040518060400160405280836000815181106102b5576102b56108d9565b60200260200101518152602001836001815181106102d5576102d56108d9565b6020908102919091018101519091526001600160a01b03851660009081526002808352604082208054600181018255908352929091208351939450849392909102019081906103249082610974565b50602082015160018201906103399082610974565b505050505050565b6001600160a01b0381166000908152600260205260409020546060906103a95760405162461bcd60e51b815260206004820181905260248201527f6e6f6e206861206566666574747561746f206e657373756e206163636573736f604482015260640161011a565b6001600160a01b03821660009081526002602052604081208054610518919083906103d6576103d66108d9565b90600052602060002090600202016040518060400160405290816000820180546103ff906108ef565b80601f016020809104026020016040519081016040528092919081815260200182805461042b906108ef565b80156104785780601f1061044d57610100808354040283529160200191610478565b820191906000526020600020905b81548152906001019060200180831161045b57829003601f168201915b50505050508152602001600182018054610491906108ef565b80601f01602080910402602001604051908101604052809291908181526020018280546104bd906108ef565b801561050a5780601f106104df5761010080835404028352916020019161050a565b820191906000526020600020905b8154815290600101906020018083116104ed57829003601f168201915b5050505050815250506105eb565b6040516020016105289190610a34565b60408051601f19818403018152919052905060015b6001600160a01b0384166000908152600260205260409020548110156105c2576001600160a01b03841660009081526002602052604090208054839161058d91849081106103d6576103d66108d9565b60405160200161059e929190610a5d565b604051602081830303815290604052915080806105ba906108c0565b91505061053d565b50806040516020016105d49190610a99565b60408051601f198184030181529190529392505050565b602080820151825160405160609361060593929101610abe565b6040516020818303038152906040529050919050565b60006020828403121561062d57600080fd5b813560ff8116811461063e57600080fd5b9392505050565b80356001600160a01b038116811461065c57600080fd5b919050565b634e487b7160e01b600052604160045260246000fd5b604051601f8201601f1916810167ffffffffffffffff811182821017156106a0576106a0610661565b604052919050565b60008060408084860312156106bc57600080fd5b6106c584610645565b925060208085013567ffffffffffffffff808211156106e357600080fd5b8187019150601f88818401126106f857600080fd5b82358281111561070a5761070a610661565b8060051b610719868201610677565b918252848101860191868101908c84111561073357600080fd5b87870192505b838310156107c0578235868111156107515760008081fd5b8701603f81018e136107635760008081fd5b888101358781111561077757610777610661565b610788818801601f19168b01610677565b8181528f8c83850101111561079d5760008081fd5b818c84018c83013760009181018b01919091528352509187019190870190610739565b8099505050505050505050509250929050565b6000602082840312156107e557600080fd5b61063e82610645565b60005b838110156108095781810151838201526020016107f1565b50506000910152565b60208152600082518060208401526108318160408501602087016107ee565b601f01601f19169190910160400192915050565b634e487b7160e01b600052601160045260246000fd5b808202811582820484141761087257610872610845565b92915050565b8181038181111561087257610872610845565b8082018082111561087257610872610845565b6000826108bb57634e487b7160e01b600052601260045260246000fd5b500490565b6000600182016108d2576108d2610845565b5060010190565b634e487b7160e01b600052603260045260246000fd5b600181811c9082168061090357607f821691505b60208210810361092357634e487b7160e01b600052602260045260246000fd5b50919050565b601f82111561096f57600081815260208120601f850160051c810160208610156109505750805b601f850160051c820191505b818110156103395782815560010161095c565b505050565b815167ffffffffffffffff81111561098e5761098e610661565b6109a28161099c84546108ef565b84610929565b602080601f8311600181146109d757600084156109bf5750858301515b600019600386901b1c1916600185901b178555610339565b600085815260208120601f198616915b82811015610a06578886015182559484019460019091019084016109e7565b5085821015610a245787850151600019600388901b60f8161c191681555b5050505050600190811b01905550565b605b60f81b815260008251610a508160018501602087016107ee565b9190910160010192915050565b60008351610a6f8184602088016107ee565b600b60fa1b9083019081528351610a8d8160018401602088016107ee565b01600101949350505050565b60008251610aab8184602087016107ee565b605d60f81b920191825250600101919050565b6a3db7b930a637b3b4b71d1160a91b81528251600090610ae581600b8501602088016107ee565b681116103437b9ba1d1160b91b600b918401918201528351610b0e8160148401602088016107ee565b61227d60f01b6014929091019182015260160194935050505056fea2646970667358221220f58097dafd63ce69c5c001c6ddf4e44fde081978c7aba333d8695f3163ff64c264736f6c63430008120033";

    public static final String FUNC_GETINFOACCESSO = "getInfoAccesso";

    public static final String FUNC_GETMEDIA = "getMedia";

    public static final String FUNC_INSERTACCESSO = "insertAccesso";

    public static final String FUNC_INSERTVOTO = "insertVoto";

    @Deprecated
    protected Contracts_AuthBlockLite_sol_AuthBlockLite(String contractAddress, Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit) {
        super(BINARY, contractAddress, web3j, credentials, gasPrice, gasLimit);
    }

    protected Contracts_AuthBlockLite_sol_AuthBlockLite(String contractAddress, Web3j web3j, Credentials credentials, ContractGasProvider contractGasProvider) {
        super(BINARY, contractAddress, web3j, credentials, contractGasProvider);
    }

    @Deprecated
    protected Contracts_AuthBlockLite_sol_AuthBlockLite(String contractAddress, Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        super(BINARY, contractAddress, web3j, transactionManager, gasPrice, gasLimit);
    }

    protected Contracts_AuthBlockLite_sol_AuthBlockLite(String contractAddress, Web3j web3j, TransactionManager transactionManager, ContractGasProvider contractGasProvider) {
        super(BINARY, contractAddress, web3j, transactionManager, contractGasProvider);
    }

    public RemoteFunctionCall<String> getInfoAccesso(String indirizzo) {
        final Function function = new Function(FUNC_GETINFOACCESSO, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.Address(160, indirizzo)), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Utf8String>() {}));
        return executeRemoteCallSingleValueReturn(function, String.class);
    }

    public RemoteFunctionCall<BigInteger> getMedia() {
        final Function function = new Function(FUNC_GETMEDIA, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}));
        return executeRemoteCallSingleValueReturn(function, BigInteger.class);
    }

    public RemoteFunctionCall<TransactionReceipt> insertAccesso(String indirizzo, List<String> _dataInfoAccesso, BigInteger weiValue) {
        final Function function = new Function(
                FUNC_INSERTACCESSO, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.Address(160, indirizzo), 
                new org.web3j.abi.datatypes.DynamicArray<org.web3j.abi.datatypes.Utf8String>(
                        org.web3j.abi.datatypes.Utf8String.class,
                        org.web3j.abi.Utils.typeMap(_dataInfoAccesso, org.web3j.abi.datatypes.Utf8String.class))), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function, weiValue);
    }

    public RemoteFunctionCall<TransactionReceipt> insertVoto(BigInteger vot) {
        final Function function = new Function(
                FUNC_INSERTVOTO, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.generated.Uint8(vot)), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    @Deprecated
    public static Contracts_AuthBlockLite_sol_AuthBlockLite load(String contractAddress, Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit) {
        return new Contracts_AuthBlockLite_sol_AuthBlockLite(contractAddress, web3j, credentials, gasPrice, gasLimit);
    }

    @Deprecated
    public static Contracts_AuthBlockLite_sol_AuthBlockLite load(String contractAddress, Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        return new Contracts_AuthBlockLite_sol_AuthBlockLite(contractAddress, web3j, transactionManager, gasPrice, gasLimit);
    }

    public static Contracts_AuthBlockLite_sol_AuthBlockLite load(String contractAddress, Web3j web3j, Credentials credentials, ContractGasProvider contractGasProvider) {
        return new Contracts_AuthBlockLite_sol_AuthBlockLite(contractAddress, web3j, credentials, contractGasProvider);
    }

    public static Contracts_AuthBlockLite_sol_AuthBlockLite load(String contractAddress, Web3j web3j, TransactionManager transactionManager, ContractGasProvider contractGasProvider) {
        return new Contracts_AuthBlockLite_sol_AuthBlockLite(contractAddress, web3j, transactionManager, contractGasProvider);
    }

    public static RemoteCall<Contracts_AuthBlockLite_sol_AuthBlockLite> deploy(Web3j web3j, Credentials credentials, ContractGasProvider contractGasProvider) {
        return deployRemoteCall(Contracts_AuthBlockLite_sol_AuthBlockLite.class, web3j, credentials, contractGasProvider, BINARY, "");
    }

    @Deprecated
    public static RemoteCall<Contracts_AuthBlockLite_sol_AuthBlockLite> deploy(Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit) {
        return deployRemoteCall(Contracts_AuthBlockLite_sol_AuthBlockLite.class, web3j, credentials, gasPrice, gasLimit, BINARY, "");
    }

    public static RemoteCall<Contracts_AuthBlockLite_sol_AuthBlockLite> deploy(Web3j web3j, TransactionManager transactionManager, ContractGasProvider contractGasProvider) {
        return deployRemoteCall(Contracts_AuthBlockLite_sol_AuthBlockLite.class, web3j, transactionManager, contractGasProvider, BINARY, "");
    }

    @Deprecated
    public static RemoteCall<Contracts_AuthBlockLite_sol_AuthBlockLite> deploy(Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        return deployRemoteCall(Contracts_AuthBlockLite_sol_AuthBlockLite.class, web3j, transactionManager, gasPrice, gasLimit, BINARY, "");
    }
}
