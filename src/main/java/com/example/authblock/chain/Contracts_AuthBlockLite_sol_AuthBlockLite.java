package com.example.authblock.chain;

import java.math.BigInteger;
import java.util.Arrays;
import java.util.Collections;
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
    public static final String BINARY = "608060405234801561001057600080fd5b50610af6806100206000396000f3fe60806040526004361061003f5760003560e01c806336c0adec146100445780634307199e14610066578063b707b6dd1461008e578063c35cea87146100b0575b600080fd5b34801561005057600080fd5b5061006461005f366004610696565b6100c3565b005b34801561007257600080fd5b5061007b610203565b6040519081526020015b60405180910390f35b34801561009a57600080fd5b506100a3610223565b60405161008591906106e4565b6100646100be36600461072d565b610419565b60018160ff16101561011c5760405162461bcd60e51b815260206004820152601960248201527f20496c20766f746f2065272074726f70706f20626173736f200000000000000060448201526064015b60405180910390fd5b60058160ff1611156101705760405162461bcd60e51b815260206004820152601860248201527f494c20766f746f206527206d616767696f7265206469203500000000000000006044820152606401610113565b3360009081526003602052604090205460ff16156101bc57600154336000908152600360205260409020546101b4916101ab9160ff166104ba565b8260ff166104cf565b6001556101e0565b6101cb6001548260ff166104cf565b60019081556002546101dc916104cf565b6002555b336000908152600360205260409020805460ff191660ff92909216919091179055565b600061021e6102166103e86001546104db565b6002546104e7565b905090565b3360009081526020819052604090205460609061027b5760405162461bcd60e51b81526020600482015260166024820152756e657373756e206163636573736f2074726f7661746f60501b6044820152606401610113565b33600090815260208190526040812080546103599190839061029f5761029f6107de565b90600052602060002090600202016040518060400160405290816000820180546102c8906107f4565b80601f01602080910402602001604051908101604052809291908181526020018280546102f4906107f4565b80156103415780601f1061031657610100808354040283529160200191610341565b820191906000526020600020905b81548152906001019060200180831161032457829003601f168201915b505050505081526020016001820154815250506104f3565b604051602001610369919061082e565b60408051601f19818403018152919052905060015b336000908152602081905260409020548110156103f157336000908152602081905260409020805483916103bc918490811061029f5761029f6107de565b6040516020016103cd929190610857565b604051602081830303815290604052915080806103e9906108a9565b91505061037e565b508060405160200161040391906108c2565b60408051601f1981840301815291905292915050565b6601c6bf5263400034101561045f5760405162461bcd60e51b815260206004820152600c60248201526b32b93937b91039b2b73232b960a11b6044820152606401610113565b604080518082018252828152426020808301919091523360009081528082529283208054600181018255908452922081519192839260029091029091019081906104a99082610936565b506020820151816001015550505050565b60006104c682846109f6565b90505b92915050565b60006104c68284610a09565b60006104c68284610a1c565b60006104c68284610a33565b6060610502826020015161052b565b8251604051610515929190602001610a55565b6040516020818303038152906040529050919050565b60606000610538836105be565b600101905060008167ffffffffffffffff81111561055857610558610717565b6040519080825280601f01601f191660200182016040528015610582576020820181803683370190505b5090508181016020015b600019016f181899199a1a9b1b9c1cb0b131b232b360811b600a86061a8153600a850494508461058c57509392505050565b60008072184f03e93ff9f4daa797ed6e38ed64bf6a1f0160401b83106105fd5772184f03e93ff9f4daa797ed6e38ed64bf6a1f0160401b830492506040015b6d04ee2d6d415b85acef81000000008310610629576d04ee2d6d415b85acef8100000000830492506020015b662386f26fc10000831061064757662386f26fc10000830492506010015b6305f5e100831061065f576305f5e100830492506008015b612710831061067357612710830492506004015b60648310610685576064830492506002015b600a83106104c95760010192915050565b6000602082840312156106a857600080fd5b813560ff811681146106b957600080fd5b9392505050565b60005b838110156106db5781810151838201526020016106c3565b50506000910152565b60208152600082518060208401526107038160408501602087016106c0565b601f01601f19169190910160400192915050565b634e487b7160e01b600052604160045260246000fd5b60006020828403121561073f57600080fd5b813567ffffffffffffffff8082111561075757600080fd5b818401915084601f83011261076b57600080fd5b81358181111561077d5761077d610717565b604051601f8201601f19908116603f011681019083821181831017156107a5576107a5610717565b816040528281528760208487010111156107be57600080fd5b826020860160208301376000928101602001929092525095945050505050565b634e487b7160e01b600052603260045260246000fd5b600181811c9082168061080857607f821691505b60208210810361082857634e487b7160e01b600052602260045260246000fd5b50919050565b605b60f81b81526000825161084a8160018501602087016106c0565b9190910160010192915050565b600083516108698184602088016106c0565b600b60fa1b90830190815283516108878160018401602088016106c0565b01600101949350505050565b634e487b7160e01b600052601160045260246000fd5b6000600182016108bb576108bb610893565b5060010190565b600082516108d48184602087016106c0565b605d60f81b920191825250600101919050565b601f82111561093157600081815260208120601f850160051c8101602086101561090e5750805b601f850160051c820191505b8181101561092d5782815560010161091a565b5050505b505050565b815167ffffffffffffffff81111561095057610950610717565b6109648161095e84546107f4565b846108e7565b602080601f83116001811461099957600084156109815750858301515b600019600386901b1c1916600185901b17855561092d565b600085815260208120601f198616915b828110156109c8578886015182559484019460019091019084016109a9565b50858210156109e65787850151600019600388901b60f8161c191681555b5050505050600190811b01905550565b818103818111156104c9576104c9610893565b808201808211156104c9576104c9610893565b80820281158282048414176104c9576104c9610893565b600082610a5057634e487b7160e01b600052601260045260246000fd5b500490565b6a3db7b930a637b3b4b71d1160a91b81528251600090610a7c81600b8501602088016106c0565b681116103437b9ba1d1160b91b600b918401918201528351610aa58160148401602088016106c0565b61227d60f01b6014929091019182015260160194935050505056fea2646970667358221220dc8de4ed342351ca16a4754732f6875ee0da48bdef26b1d109003da663a288a564736f6c63430008120033";

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

    public RemoteFunctionCall<String> getInfoAccesso() {
        final Function function = new Function(FUNC_GETINFOACCESSO, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Utf8String>() {}));
        return executeRemoteCallSingleValueReturn(function, String.class);
    }

    public RemoteFunctionCall<BigInteger> getMedia() {
        final Function function = new Function(FUNC_GETMEDIA, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}));
        return executeRemoteCallSingleValueReturn(function, BigInteger.class);
    }

    public RemoteFunctionCall<TransactionReceipt> insertAccesso(String host, BigInteger weiValue) {
        final Function function = new Function(
                FUNC_INSERTACCESSO, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.Utf8String(host)), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function, weiValue);
    }

    public RemoteFunctionCall<TransactionReceipt> insertVoto(BigInteger nuovoVoto) {
        final Function function = new Function(
                FUNC_INSERTVOTO, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.generated.Uint8(nuovoVoto)), 
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
