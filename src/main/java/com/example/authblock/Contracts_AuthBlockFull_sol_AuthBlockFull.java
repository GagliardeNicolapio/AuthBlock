package com.example.authblock;

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
public class Contracts_AuthBlockFull_sol_AuthBlockFull extends Contract {
    public static final String BINARY = "608060405234801561001057600080fd5b50610ac7806100206000396000f3fe608060405234801561001057600080fd5b50600436106100415760003560e01c806350f18633146100465780639a133fae1461005b578063bf0e054a14610084575b600080fd5b61005961005436600461079f565b6100bb565b005b61006e610069366004610824565b6102de565b60405161007b9190610872565b60405180910390f35b6100ad6100923660046108a5565b6001600160a01b031660009081526020819052604090205490565b60405190815260200161007b565b60006040518060a00160405280846000815181106100db576100db6108c7565b60200260200101518152602001846001815181106100fb576100fb6108c7565b602002602001015181526020018460028151811061011b5761011b6108c7565b602002602001015181526020018460038151811061013b5761013b6108c7565b602002602001015181526020018460048151811061015b5761015b6108c7565b602002602001015181525090506000604051806060016040528084600081518110610188576101886108c7565b60200260200101518152602001846002815181106101a8576101a86108c7565b60200260200101518152602001846002815181106101c8576101c86108c7565b6020908102919091018101519091526001600160a01b0388166000908152808252604081208054600181018255908252919020845192935084926005909202019081906102159082610966565b506020820151600182019061022a9082610966565b506040820151600282019061023f9082610966565b50606082015160038201906102549082610966565b50608082015160048201906102699082610966565b5050506001600160a01b03851660009081526001602081815260408320805492830181558352909120825183926003029091019081906102a99082610966565b50602082015160018201906102be9082610966565b50604082015160028201906102d39082610966565b505050505050505050565b6001600160a01b0382166000908152602081905260408120805460609291908490811061030d5761030d6108c7565b90600052602060002090600502016040518060a0016040529081600082018054610336906108dd565b80601f0160208091040260200160405190810160405280929190818152602001828054610362906108dd565b80156103af5780601f10610384576101008083540402835291602001916103af565b820191906000526020600020905b81548152906001019060200180831161039257829003601f168201915b505050505081526020016001820180546103c8906108dd565b80601f01602080910402602001604051908101604052809291908181526020018280546103f4906108dd565b80156104415780601f1061041657610100808354040283529160200191610441565b820191906000526020600020905b81548152906001019060200180831161042457829003601f168201915b5050505050815260200160028201805461045a906108dd565b80601f0160208091040260200160405190810160405280929190818152602001828054610486906108dd565b80156104d35780601f106104a8576101008083540402835291602001916104d3565b820191906000526020600020905b8154815290600101906020018083116104b657829003601f168201915b505050505081526020016003820180546104ec906108dd565b80601f0160208091040260200160405190810160405280929190818152602001828054610518906108dd565b80156105655780601f1061053a57610100808354040283529160200191610565565b820191906000526020600020905b81548152906001019060200180831161054857829003601f168201915b5050505050815260200160048201805461057e906108dd565b80601f01602080910402602001604051908101604052809291908181526020018280546105aa906108dd565b80156105f75780601f106105cc576101008083540402835291602001916105f7565b820191906000526020600020905b8154815290600101906020018083116105da57829003601f168201915b50505050508152505090508060000151816020015182604001518360600151846080015160405160200161062f959493929190610a26565b60405160208183030381529060405291505092915050565b80356001600160a01b038116811461065e57600080fd5b919050565b634e487b7160e01b600052604160045260246000fd5b604051601f8201601f1916810167ffffffffffffffff811182821017156106a2576106a2610663565b604052919050565b6000601f83818401126106bc57600080fd5b8235602067ffffffffffffffff808311156106d9576106d9610663565b8260051b6106e8838201610679565b938452868101830193838101908986111561070257600080fd5b84890192505b85831015610792578235848111156107205760008081fd5b8901603f81018b136107325760008081fd5b8581013560408682111561074857610748610663565b610759828b01601f19168901610679565b8281528d8284860101111561076e5760008081fd5b828285018a8301376000928101890192909252508352509184019190840190610708565b9998505050505050505050565b600080600080608085870312156107b557600080fd5b6107be85610647565b93506107cc60208601610647565b9250604085013567ffffffffffffffff808211156107e957600080fd5b6107f5888389016106aa565b9350606087013591508082111561080b57600080fd5b50610818878288016106aa565b91505092959194509250565b6000806040838503121561083757600080fd5b61084083610647565b946020939093013593505050565b60005b83811015610869578181015183820152602001610851565b50506000910152565b602081526000825180602084015261089181604085016020870161084e565b601f01601f19169190910160400192915050565b6000602082840312156108b757600080fd5b6108c082610647565b9392505050565b634e487b7160e01b600052603260045260246000fd5b600181811c908216806108f157607f821691505b60208210810361091157634e487b7160e01b600052602260045260246000fd5b50919050565b601f82111561096157600081815260208120601f850160051c8101602086101561093e5750805b601f850160051c820191505b8181101561095d5782815560010161094a565b5050505b505050565b815167ffffffffffffffff81111561098057610980610663565b6109948161098e84546108dd565b84610917565b602080601f8311600181146109c957600084156109b15750858301515b600019600386901b1c1916600185901b17855561095d565b600085815260208120601f198616915b828110156109f8578886015182559484019460019091019084016109d9565b5085821015610a165787850151600019600388901b60f8161c191681555b5050505050600190811b01905550565b60008651610a38818460208b0161084e565b865190830190610a4c818360208b0161084e565b8651910190610a5f818360208a0161084e565b8551910190610a7281836020890161084e565b8451910190610a8581836020880161084e565b0197965050505050505056fea264697066735822122062c62b48dc639b3b20e22fb94b55a63cccce8e1b81d248c275a4243e9632c04064736f6c63430008110033";

    public static final String FUNC_GETINFOACCESSOSITO = "getInfoAccessoSito";

    public static final String FUNC_GETNUMBERACCESSISITO = "getNumberAccessiSito";

    public static final String FUNC_INSERTACCESSO = "insertAccesso";

    @Deprecated
    protected Contracts_AuthBlockFull_sol_AuthBlockFull(String contractAddress, Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit) {
        super(BINARY, contractAddress, web3j, credentials, gasPrice, gasLimit);
    }

    protected Contracts_AuthBlockFull_sol_AuthBlockFull(String contractAddress, Web3j web3j, Credentials credentials, ContractGasProvider contractGasProvider) {
        super(BINARY, contractAddress, web3j, credentials, contractGasProvider);
    }

    @Deprecated
    protected Contracts_AuthBlockFull_sol_AuthBlockFull(String contractAddress, Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        super(BINARY, contractAddress, web3j, transactionManager, gasPrice, gasLimit);
    }

    protected Contracts_AuthBlockFull_sol_AuthBlockFull(String contractAddress, Web3j web3j, TransactionManager transactionManager, ContractGasProvider contractGasProvider) {
        super(BINARY, contractAddress, web3j, transactionManager, contractGasProvider);
    }

    public RemoteFunctionCall<String> getInfoAccessoSito(String indirizzoSito, BigInteger accessNumber) {
        final Function function = new Function(FUNC_GETINFOACCESSOSITO, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.Address(160, indirizzoSito), 
                new org.web3j.abi.datatypes.generated.Uint256(accessNumber)), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Utf8String>() {}));
        return executeRemoteCallSingleValueReturn(function, String.class);
    }

    public RemoteFunctionCall<BigInteger> getNumberAccessiSito(String indirizzoSito) {
        final Function function = new Function(FUNC_GETNUMBERACCESSISITO, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.Address(160, indirizzoSito)), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}));
        return executeRemoteCallSingleValueReturn(function, BigInteger.class);
    }

    public RemoteFunctionCall<TransactionReceipt> insertAccesso(String _indirizzoSito, String _indirizzoUtente, List<String> _dataInfoAccessoSito, List<String> _dataInfoAccessoUtente) {
        final Function function = new Function(
                FUNC_INSERTACCESSO, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.Address(160, _indirizzoSito), 
                new org.web3j.abi.datatypes.Address(160, _indirizzoUtente), 
                new org.web3j.abi.datatypes.DynamicArray<org.web3j.abi.datatypes.Utf8String>(
                        org.web3j.abi.datatypes.Utf8String.class,
                        org.web3j.abi.Utils.typeMap(_dataInfoAccessoSito, org.web3j.abi.datatypes.Utf8String.class)), 
                new org.web3j.abi.datatypes.DynamicArray<org.web3j.abi.datatypes.Utf8String>(
                        org.web3j.abi.datatypes.Utf8String.class,
                        org.web3j.abi.Utils.typeMap(_dataInfoAccessoUtente, org.web3j.abi.datatypes.Utf8String.class))), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    @Deprecated
    public static Contracts_AuthBlockFull_sol_AuthBlockFull load(String contractAddress, Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit) {
        return new Contracts_AuthBlockFull_sol_AuthBlockFull(contractAddress, web3j, credentials, gasPrice, gasLimit);
    }

    @Deprecated
    public static Contracts_AuthBlockFull_sol_AuthBlockFull load(String contractAddress, Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        return new Contracts_AuthBlockFull_sol_AuthBlockFull(contractAddress, web3j, transactionManager, gasPrice, gasLimit);
    }

    public static Contracts_AuthBlockFull_sol_AuthBlockFull load(String contractAddress, Web3j web3j, Credentials credentials, ContractGasProvider contractGasProvider) {
        return new Contracts_AuthBlockFull_sol_AuthBlockFull(contractAddress, web3j, credentials, contractGasProvider);
    }

    public static Contracts_AuthBlockFull_sol_AuthBlockFull load(String contractAddress, Web3j web3j, TransactionManager transactionManager, ContractGasProvider contractGasProvider) {
        return new Contracts_AuthBlockFull_sol_AuthBlockFull(contractAddress, web3j, transactionManager, contractGasProvider);
    }

    public static RemoteCall<Contracts_AuthBlockFull_sol_AuthBlockFull> deploy(Web3j web3j, Credentials credentials, ContractGasProvider contractGasProvider) {
        return deployRemoteCall(Contracts_AuthBlockFull_sol_AuthBlockFull.class, web3j, credentials, contractGasProvider, BINARY, "");
    }

    @Deprecated
    public static RemoteCall<Contracts_AuthBlockFull_sol_AuthBlockFull> deploy(Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit) {
        return deployRemoteCall(Contracts_AuthBlockFull_sol_AuthBlockFull.class, web3j, credentials, gasPrice, gasLimit, BINARY, "");
    }

    public static RemoteCall<Contracts_AuthBlockFull_sol_AuthBlockFull> deploy(Web3j web3j, TransactionManager transactionManager, ContractGasProvider contractGasProvider) {
        return deployRemoteCall(Contracts_AuthBlockFull_sol_AuthBlockFull.class, web3j, transactionManager, contractGasProvider, BINARY, "");
    }

    @Deprecated
    public static RemoteCall<Contracts_AuthBlockFull_sol_AuthBlockFull> deploy(Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        return deployRemoteCall(Contracts_AuthBlockFull_sol_AuthBlockFull.class, web3j, transactionManager, gasPrice, gasLimit, BINARY, "");
    }
}
